package com.calendar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.calendar.domain.Entry;
import com.calendar.domain.User;
import com.calendar.repository.EntryRepository;
import com.calendar.requestdto.EntryDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.service.EntryService;

@Service
public class EntryServiceImpl implements EntryService {

	private EntryRepository entryRepository;
	private UserServiceImpl userServiceImpl;

	@Autowired
	public EntryServiceImpl(EntryRepository entryRepository, UserServiceImpl userServiceImpl) {
		this.entryRepository = entryRepository;
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	public void createFirstEntry(EntryDto entryDto) {
		
		User user = userServiceImpl.getFullUser();
		
		Entry entry = createEntryWithLvlOfHierarchy(entryDto, 1);
		entry.setUserId(user.getId());
		user.addEntry(entry);
		
		entryRepository.save(entry);
	}

	@Override
	public EntryResponseDto getEntries() {
		
		EntryResponseDto entryResponseDto = new EntryResponseDto();
		
		User user = userServiceImpl.getFullUser();
		
		entryResponseDto.setEntryList(user.getEntryList());
		
		return entryResponseDto;
	}
	
//	creates a new Entry from EntryDto with a number in hierarchy
	private Entry createEntryWithLvlOfHierarchy(EntryDto entryDto, int lvl) {
		Entry entry = new Entry(entryDto.getTitle(), entryDto.getDescription(), entryDto.getDate(), entryDto.getDuration(), entryDto.getTermin(), 
				EntryType.valueOf(entryDto.getEntryType()) , EntryPhase.valueOf(entryDto.getEntryPhase()), 
				lvl);
		return entry;
	}
	
}
