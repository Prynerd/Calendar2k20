package com.calendar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.calendar.domain.Entry;
import com.calendar.domain.User;
import com.calendar.repository.EntryRepository;
import com.calendar.repository.custom.CustomEntryRepository;
import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.EntryListResponseDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.responsedto.FullProjectResponseDto;
import com.calendar.responsedto.ProjektEntriesResponseDto;
import com.calendar.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

	private EntryRepository entryRepository;
	private CustomEntryRepository customEntryRepository;
	private UserServiceImpl userServiceImpl;

	@Autowired
	public EntryServiceImpl(EntryRepository entryRepository, UserServiceImpl userServiceImpl,
			CustomEntryRepository customEntryRepository) {
		this.entryRepository = entryRepository;
		this.userServiceImpl = userServiceImpl;
		this.customEntryRepository = customEntryRepository;
	}

	@Override
	@Transactional
	public void createProject(ProjectDto projectDto) {

		User user = userServiceImpl.getFullUser();
		
		Entry entry = new Entry(projectDto.getTitle(), projectDto.getDescription(), null, null, projectDto.getTermin(),
				EntryType.NONRELEVANT, EntryPhase.NONRELEVANT);
		
		entry.setUserId(user.getId());

		Integer numberOfProjects = customEntryRepository.getProjectsByUserIdAndStatus(user.getId(), false).size();
		entry.setSortNumber((numberOfProjects != null) ?  numberOfProjects : 0);
		
		entryRepository.save(entry);

	}

	@Override
	@Transactional
	public void createEntry(EntryDto entryDto) {

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

		Integer numberOfEntriesOnThisLevel = entryRepository.findById(entryDto.getAddedEntryId()).get().getAddEntry().size();
		entry.setSortNumber((numberOfEntriesOnThisLevel != null) ?  numberOfEntriesOnThisLevel : 0);
		
		entryRepository.save(entry);
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
	public ArrayList<ProjektEntriesResponseDto> getProjekts(boolean isFinished) {

		User user = userServiceImpl.getFullUser();
		List<Entry> entryList = new ArrayList<Entry>();
		entryList = customEntryRepository.getProjectsByUserIdAndStatus(user.getId(), isFinished);

		ArrayList<ProjektEntriesResponseDto> perDtoList = new ArrayList<ProjektEntriesResponseDto>();
		for (int i = 0; i < entryList.size(); i++) {
			Entry entry = entryList.get(i);
			ProjektEntriesResponseDto perDto = new ProjektEntriesResponseDto(entry.getId(), entry.getTitle(),
					entry.getEntryPhase());
			perDtoList.add(perDto);
		}

		return perDtoList;
	}

	@Override
	public EntryResponseDto getEntryById(int id) {

		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();
		EntryResponseDto erDto = new EntryResponseDto(e.getId(), e.getUserId(), e.getTitle(), e.getDescription(),
				e.getDate(), e.getDuration(), e.getTermin(), e.getEntryType(), e.getEntryPhase(), e.isChild(),
				e.isFinished(), e.getSortNumber());

		User user = userServiceImpl.getFullUser();
		if(user.getId() != erDto.getUserId()) {
			throw new AccessDeniedException("Access denied");
		}
		
		return erDto;
	}

	@Override
	public FullProjectResponseDto getFullProjectById(int id) {

		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();
		
		checkUserToEntry(e);
		
		return new FullProjectResponseDto(e.getId(), e.getUserId(), e.getTitle(), e.getDescription(), e.getDate(),
				e.getDuration(), e.getTermin(), e.getEntryType(), e.getEntryPhase(), e.isChild(), e.isFinished(),
				e.getAddEntry());
	}

	@Override
	@Transactional
	public void deleteEntryById(int id) {

		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();

		checkUserToEntry(e);
		
		customEntryRepository.removeEntry(e);
	}

	@Override
	@Transactional
	public void modifyEntryById(int id, EntryDto eDto) {

		Entry entry = entryRepository.findById(id).get();
		
		checkUserToEntry(entry);
		
		entry.setTitle(eDto.getTitle());
		entry.setDescription(eDto.getDescription());
		entry.setDate(eDto.getDate());
		entry.setDuration(eDto.getDuration());
		entry.setTermin(eDto.getTermin());
		entry.setEntryPhase(EntryPhase.valueOf(eDto.getEntryPhase()));
		entry.setEntryType(EntryType.valueOf(eDto.getEntryType()));
		
		entryRepository.save(entry);
	}

	@Override
	@Transactional
	public void modifyProjectById(int id, ProjectDto projectDto) {

		Entry project = entryRepository.findById(id).get();
		
		checkUserToEntry(project);
		
		project.setTitle(projectDto.getTitle());
		project.setDescription(projectDto.getDescription());
		
		entryRepository.save(project);
	}

	public void checkUserToEntry(Entry e) {
		User user = userServiceImpl.getFullUser();
		if(user.getId() != e.getUserId()) {
			throw new AccessDeniedException("Access denied");
		}
	}
	
}
