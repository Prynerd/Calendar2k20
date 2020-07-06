package com.calendar.responsedto;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.calendar.domain.Entry;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FullProjectResponseDto {

	private int id;
	
	private int userId;
	
	private String title;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime date;

	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime duration;

	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime termin;

	@Enumerated(EnumType.STRING)
	private EntryType entryType;

	@Enumerated(EnumType.STRING)
	private EntryPhase entryPhase;
	
	private boolean isChild;
	
	private boolean isClosed;
	
	private boolean isDeleted;
	
	private Integer sortNumber;

	private boolean isExpanded;

	private Set<Entry> addEntry = new HashSet<Entry>();
	
	public FullProjectResponseDto(
			int id, int userId, String title, String description, LocalDateTime date, LocalDateTime duration,
			LocalDateTime termin, EntryType entryType, EntryPhase entryPhase, boolean isChild, boolean isClosed,
			boolean isDeleted, Integer sortNumber, boolean isExpanded, Set<Entry> addEntry) {
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.termin = termin;
		this.entryType = entryType;
		this.entryPhase = entryPhase;
		this.isChild = isChild;
		this.isClosed = isClosed;
		this.isDeleted = isDeleted;
		this.sortNumber = sortNumber;
		this.isExpanded = isExpanded;
		this.addEntry = addEntry;
	}
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public void setAddEntry(Set<Entry> addEntry) {
		this.addEntry = addEntry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getDuration() {
		return duration;
	}

	public void setDuration(LocalDateTime duration) {
		this.duration = duration;
	}

	public LocalDateTime getTermin() {
		return termin;
	}

	public void setTermin(LocalDateTime termin) {
		this.termin = termin;
	}

	public EntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(EntryType entryType) {
		this.entryType = entryType;
	}

	public EntryPhase getEntryPhase() {
		return entryPhase;
	}

	public void setEntryPhase(EntryPhase entryPhase) {
		this.entryPhase = entryPhase;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	public Set<Entry> getAddEntry() {
		return addEntry;
	}

	public void AddEntry(Set<Entry> addEntry) {
		this.addEntry = addEntry;
	}
	
}
