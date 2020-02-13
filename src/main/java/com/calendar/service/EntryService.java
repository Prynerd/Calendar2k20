package com.calendar.service;

import com.calendar.requestdto.EntryDto;
import com.calendar.responsedto.EntryListResponseDto;
import com.calendar.responsedto.EntryResponseDto;

public interface EntryService {

	public void createEntry(EntryDto entryDto);
	
	public EntryListResponseDto getEntries();
	
	public EntryResponseDto getEntryById(int id);
}
