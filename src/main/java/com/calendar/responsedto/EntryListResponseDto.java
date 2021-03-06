package com.calendar.responsedto;

import java.util.List;

import com.calendar.domain.Entry;

public class EntryListResponseDto {

	private List<Entry> entryList;
	
	public EntryListResponseDto() {
		
	}

	public void addEntry(Entry entry) {
		entryList.add(entry);
	}

	public List<Entry> getEntryList() {
		return entryList;
	}

	public void setEntryList(List<Entry> entryList) {
		this.entryList = entryList;
	}
	
}
