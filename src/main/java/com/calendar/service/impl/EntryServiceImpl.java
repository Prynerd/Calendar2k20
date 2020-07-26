package com.calendar.service.impl;

import com.calendar.dao.EntryDao;
import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.calendar.domain.Entry;
import com.calendar.domain.User;
import com.calendar.exceptions.EntryNotFoundException;
import com.calendar.repository.EntryRepository;
import com.calendar.repository.custom.CustomEntryRepository;
import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.EntryForModificationDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.*;
import com.calendar.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class EntryServiceImpl implements EntryService {

	private EntryRepository entryRepository;
	private CustomEntryRepository customEntryRepository;
	private UserServiceImpl userServiceImpl;
	private EntryDao entryDao;

	@Autowired
	public EntryServiceImpl(EntryRepository entryRepository, UserServiceImpl userServiceImpl,
			CustomEntryRepository customEntryRepository, EntryDao entryDao, EntityManager em) {
		this.entryRepository = entryRepository;
		this.userServiceImpl = userServiceImpl;
		this.customEntryRepository = customEntryRepository;
		this.entryDao = entryDao;
	}

	@Override
	@Transactional
	public ProjectViewResponseDto createProject(ProjectDto projectDto) {
		User user = userServiceImpl.getFullUser();
		
		Entry entry = new Entry(projectDto.getTitle(), projectDto.getDescription(), null, null,
				projectDto.getTermin(),
				EntryType.NONRELEVANT, EntryPhase.NONRELEVANT);
		
		entry.setUserId(user.getId());

		Integer numberOfProjects = customEntryRepository.getProjectsByUserIdAndStatus(user.getId(), false)
				.size();
		entry.setSortNumber((numberOfProjects != null) ?  numberOfProjects : 0);
		
		entryRepository.save(entry);

		//Need to create the response object here in order to avoid the recursive query() --> getProjectIdOfEntry
		return new ProjectViewResponseDto(getFullProjectById(entry.getId()), getProjects(user.isOnlyActiveProjects()));
	}

	@Override
	@Transactional
	public ProjectViewResponseDto createEntry(EntryDto entryDto) {
		User user = userServiceImpl.getFullUser();
		
		EntryType entryType;
		EntryPhase entryPhase;
		
		if (entryDto.getEntryType() != null) {
			entryType = EntryType.valueOf(entryDto.getEntryType());
		} else {
			entryType = EntryType.NONRELEVANT;
		}
		if(entryDto.getEntryPhase() != null) {
			entryPhase = EntryPhase.valueOf(entryDto.getEntryPhase());
		} else {
			entryPhase = EntryPhase.NONRELEVANT;
		}
		
		Entry entry = new Entry(
				entryDto.getTitle(), 
				entryDto.getDescription(), 
				entryDto.getDate(),
				entryDto.getDuration(), 
				entryDto.getTermin(), 
				entryType,
				entryPhase);

		entry.setUserId(user.getId());
		entry.addEntryConnection(entryRepository.getOne(entryDto.getAddedEntryId()));
		entry.setChild(true);

		Integer numberOfEntriesOnThisLevel = entryRepository.findById(entryDto.getAddedEntryId())
				.get().getAddEntry().size();
		entry.setSortNumber((numberOfEntriesOnThisLevel != null) ?  numberOfEntriesOnThisLevel : 0);

		entryRepository.save(entry);

		/* Need to manually add the currently created entry to it's parent's children, otherwise it would not be
		visible in the response. --> Recursive query
		*/
		Entry parentOfEntry = entryRepository.findById(entry.getEntryConnections().getId()).get();
		parentOfEntry.getAddEntry().add(entry);

		return getProjectView(parentOfEntry.getId());
	}

	@Override
	public EntryListResponseDto getEntries() {
		EntryListResponseDto entryResponseDto = new EntryListResponseDto();

		User user = userServiceImpl.getFullUser();
		List<Entry> entryList = new ArrayList<Entry>();
		entryList = customEntryRepository.getOrderedEntriesByUserId(user.getId());
		entryResponseDto.setEntryList(entryList);

		return entryResponseDto;
	}

	@Override
	public ArrayList<ProjectEntriesResponseDto> getProjects(boolean openOnly) {
		User user = userServiceImpl.getFullUser();
		List<Entry> entryList = new ArrayList<Entry>();
		
		if(openOnly) {
			entryList = customEntryRepository.getProjectsByUserIdAndStatus(user.getId(), false);
		} else {
			entryList = customEntryRepository.getEntriesByUserId(user.getId());
		}

		ArrayList<ProjectEntriesResponseDto> perDtoList = new ArrayList<ProjectEntriesResponseDto>();
		for (int i = 0; i < entryList.size(); i++) {
			Entry entry = entryList.get(i);
			ProjectEntriesResponseDto perDto = new ProjectEntriesResponseDto(entry.getId(), entry.getTitle(),
					entry.isClosed(), entry.getSortNumber());
			perDtoList.add(perDto);
		}

		return perDtoList;
	}

	@Override
	public FullProjectResponseDto getFullProjectById(int id) {
		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();
		
		checkUserToEntry(e);
		
		return new FullProjectResponseDto(e.getId(), e.getUserId(), e.getTitle(), e.getDescription(), e.getDate(),
				e.getDuration(), e.getTermin(), e.getEntryType(), e.getEntryPhase(), e.isChild(), e.isClosed(),
				e.isDeleted(), e.getSortNumber(), e.isExpanded(), e.getAddEntry());
	}

	@Override
	public ProjectViewResponseDto getProjectView(Integer id) {
		User user = userServiceImpl.getFullUser();

		if(id != null) {
			return new ProjectViewResponseDto(getFullProjectById(getProjectIdOfEntry(id)),
					getProjects(user.isOnlyActiveProjects()));
		} else {
			return new ProjectViewResponseDto(null, getProjects(user.isOnlyActiveProjects()));
		}
	}

	public ProjectViewResponseForModificationDto getProjectViewWithChildrenClosedStatus(Integer id,
																						boolean hasOpenChildren) {
		User user = userServiceImpl.getFullUser();

		if(id != null) {
			if (hasOpenChildren) {
				return new ProjectViewResponseForModificationDto(null, null,
						true);
			} else {
				return new ProjectViewResponseForModificationDto(getFullProjectById(getProjectIdOfEntry(id)),
						getProjects(user.isOnlyActiveProjects()), null);
			}
		} else {
			return new ProjectViewResponseForModificationDto(null,
					getProjects(user.isOnlyActiveProjects()), null);
		}

	}

	@Override
	public EntryResponseDto getEntryById(int id) {
		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();
		EntryResponseDto erDto = new EntryResponseDto(e.getId(), e.getUserId(), e.getTitle(), e.getDescription(),
				e.getDate(), e.getDuration(), e.getTermin(), e.getEntryType(), e.getEntryPhase(), e.isChild(),
				e.isClosed(), e.getSortNumber(), e.isDeleted(), e.isExpanded());

		User user = userServiceImpl.getFullUser();
		if(user.getId() != erDto.getUserId()) {
			throw new AccessDeniedException("Access denied");
		}

		return erDto;
	}

	@Override
	@Transactional
	public ProjectViewResponseDto deleteEntryById(int id) {
		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();

		checkUserToEntry(e);

		customEntryRepository.removeEntry(e);

		try {
			int parentId = e.getEntryConnections().getId();
			return getProjectView(parentId);

		} catch(NullPointerException exception) {
			return getProjectView(null);
		}

	}

	@Transactional
	public ProjectViewResponseForModificationDto modifyEntry(Entry entry, EntryForModificationDto eDto,
															 boolean checkIfAllChildrenAreClosed) {
		entry.setTitle(eDto.getTitle());

		if(eDto.getDescription() != null) {
			entry.setDescription(eDto.getDescription());
		}

		if(eDto.getDate() != null) {
			entry.setDate(eDto.getDate());
		}

		if(eDto.getDuration() != null) {
			entry.setDuration(eDto.getDuration());
		}

		if(eDto.getTermin() != null) {
			entry.setTermin(eDto.getTermin());
		}

		if(eDto.getEntryPhase() != null) {
			entry.setEntryPhase(EntryPhase.valueOf(eDto.getEntryPhase()));
		}

		if(eDto.getEntryType() != null) {
			entry.setEntryType(EntryType.valueOf(eDto.getEntryType()));
		}

		if(eDto.isClosed() != null) {
			entry.setClosed(eDto.isClosed());
		}

		entryRepository.save(entry);

		return getProjectViewWithChildrenClosedStatus(entry.getId(),checkIfAllChildrenAreClosed);
	}

	@Override
	public ProjectViewResponseForModificationDto modifyEntryById(int id, EntryForModificationDto eDto,
																 boolean checkIfAllChildrenAreClosed) {
		Entry entry = entryRepository.findById(id).get();

		checkUserToEntry(entry);

		if (!checkIfAllChildrenAreClosed) {
			return modifyEntry(entry, eDto, false);
		} else {
			if (hasOpenChildren(id)) {
				return getProjectViewWithChildrenClosedStatus(id,true);
			} else {
				return modifyEntry(entry, eDto, false);
			}
		}
	}

	@Override
	@Transactional
	public ProjectViewResponseDto modifyProjectById(int id, ProjectDto projectDto) {
		Entry project = entryRepository.findById(id).get();
		
		checkUserToEntry(project);
		
		project.setTitle(projectDto.getTitle());
		project.setDescription(projectDto.getDescription());
		
		entryRepository.save(project);
		
		return getProjectView(id);
	}

	private void checkUserToEntry(Entry e) {

		User user = userServiceImpl.getFullUser();
		if(user.getId() != e.getUserId()) {
			throw new AccessDeniedException("Access denied");
		}
	}

	@Transactional
	@Override
	public void expandEntry(int id, boolean isExpanded) {
		Entry entry;

		try {
			entry = entryRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new EntryNotFoundException("Entry doesn't exist!");
		}

		checkUserToEntry(entry);

		entry.setExpanded(isExpanded);

		entryRepository.save(entry);
	}

	private int getProjectIdOfEntry(int entryId) {
		Entry entry = entryRepository.findById(entryId).get();

		checkUserToEntry(entry);

		int parentId = entryDao.getProjectIdOfEntry(entryId);

		return parentId;
	}

    private boolean hasOpenChildren(int id) {
	    Entry entry = entryRepository.findById(id).get();

        return entry.getAddEntry().stream().anyMatch(child -> !child.isClosed());
    }


}
