package com.calendar.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
				EntryType.valueOf(projectDto.getEntryType()), EntryPhase.valueOf(projectDto.getEntryPhase()));

		entry.setUserId(user.getId());

		entryRepository.save(entry);

	}

	@Override
	@Transactional
	public void createEntry(EntryDto entryDto) {

		User user = userServiceImpl.getFullUser();

		Entry entry = new Entry(entryDto.getTitle(), entryDto.getDescription(), entryDto.getDate(),
				entryDto.getDuration(), entryDto.getTermin(), EntryType.valueOf(entryDto.getEntryType()),
				EntryPhase.valueOf(entryDto.getEntryPhase()));

		entry.setUserId(user.getId());
		entry.addEntryConnection(entryRepository.getOne(entryDto.getAddedEntryId()));
		entry.setChild(true);

		entryRepository.save(entry);
	}

	@Override
	public EntryListResponseDto getEntries() {

		EntryListResponseDto entryResponseDto = new EntryListResponseDto();

		User user = userServiceImpl.getFullUser();
		List<Entry> entryList = new ArrayList<Entry>();
		entryList = customEntryRepository.getEntriesByUserId(user.getId());
		entryResponseDto.setEntryList(entryList);

		return entryResponseDto;
	}

	@Override
	public ArrayList<ProjektEntriesResponseDto> getProjekts(boolean isFinished) {

		User user = userServiceImpl.getFullUser();
		List<Entry> entryList = new ArrayList<Entry>();
		entryList = customEntryRepository.getEntriesByUserIdAndStatus(user.getId(), isFinished);

		ArrayList<ProjektEntriesResponseDto> perDtoList = new ArrayList();
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
				e.isFinished());

		return erDto;
	}

	@Override
	public FullProjectResponseDto getFullProjectById(int id) {

		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();
		return new FullProjectResponseDto(e.getId(), e.getUserId(), e.getTitle(), e.getDescription(), e.getDate(),
				e.getDuration(), e.getTermin(), e.getEntryType(), e.getEntryPhase(), e.isChild(), e.isFinished(),
				e.getAddEntry());
	}

	@Override
	@Transactional
	public void deleteEntryById(int id) {

		Optional<Entry> entry = entryRepository.findById(id);
		Entry e = entry.get();

//		List<Integer> toDeleteList = new ArrayList<>();
//
//		getAddedEntryIds(e, toDeleteList);
//
//		if (toDeleteList.size() != 0) {
//			for (Integer idToDelete : toDeleteList) {
//				entryRepository.deleteById(idToDelete);
//			}
//		}
//		
//		entryRepository.deleteById(id);
		
		customEntryRepository.removeEntry(e);
		
		/*
		 * List<Integer> toDeleteList= new ArrayList<>();
		 * 
		 * while (e.getAddEntry().size() != 0) { Entry alt = e;
		 * 
		 * // int actualAltEntryNumber = alt.getAddEntry().size(); int counter = 0;
		 * 
		 * // while (alt.getAddEntry().size() != 0) { while (alt.getAddEntry().size() ==
		 * counter) {
		 * 
		 * for (Entry altEntry : alt.getAddEntry()) {
		 * toDeleteList.add(altEntry.getId()); alt = altEntry; } counter++; }
		 * toDeleteList.add(alt.getId()); // entryRepository.deleteById(alt.getId()); }
		 * for (Integer idToDelete : toDeleteList) {
		 * entryRepository.deleteById(idToDelete); } entryRepository.deleteById(id);
		 */
	}

	public List<Integer> hierarhicalIterator(Entry e, List<Integer> idList) {

		int size = idList.size();

		for (Entry entry : e.getAddEntry()) {
			idList.add(entry.getId());
		}

		if (size < idList.size()) {
			return idList;
		}

//		Set<Entry> set = e.getAddEntry();
//	    Iterator<Entry> entry = set.iterator();
//	    while(entry.hasNext()) {
//	        idList.add(entry.next().getId());
//	        hierarhicalIterator(entry.next());
//	        
//	    }
		return null;
	}

	public List<Integer> getAddedEntryIds(Entry e, List<Integer> toDeleteList) {
		List<Integer> actIdList = hierarhicalIterator(e, toDeleteList);

		if (actIdList != null) {
			return actIdList;
			
		}		
		return toDeleteList;

	}

}
