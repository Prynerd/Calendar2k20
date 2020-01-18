package com.calendar.service;

import com.calendar.requestdto.EntryDto;
import com.calendar.responsedto.EntryResponseDto;

public interface EntryService {

	public void createFirstEntry(EntryDto entryDto);
	
	public EntryResponseDto getEntries();
}
