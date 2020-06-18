package com.calendar.responsedto;

import com.calendar.data.enums.EntryPhase;

public class ProjectEntriesResponseDto {
	
	private int entryId;
	
	private String title;
	
	private EntryPhase entryPhase;
	
	private Integer sortNumber;
	
	public ProjectEntriesResponseDto(int entryId, String title, EntryPhase entryPhase, Integer sortNumber) {
		this.entryId = entryId;
		this.title = title;
		this.entryPhase = entryPhase;
		this.sortNumber = sortNumber;
	}
	
	

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EntryPhase getEntryPhase() {
		return entryPhase;
	}

	public void setEntryPhase(EntryPhase entryPhase) {
		this.entryPhase = entryPhase;
	}
	
}
