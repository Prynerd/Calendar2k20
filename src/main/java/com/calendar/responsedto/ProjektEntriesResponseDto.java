package com.calendar.responsedto;

import javax.persistence.Entity;

import com.calendar.data.enums.EntryPhase;

@Entity
public class ProjektEntriesResponseDto {
	
	private int entryId;
	
	private String title;
	
	private EntryPhase entryPhase;
	
	public ProjektEntriesResponseDto(int entryId, String title, EntryPhase entryPhase) {
		this.entryId = entryId;
		this.title = title;
		this.entryPhase = entryPhase;
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
