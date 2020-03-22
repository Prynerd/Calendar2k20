package com.calendar.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	
	@NotNull
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
	
	private boolean isFinished;
	
	private boolean isDeleted;
	
	private Integer sortNumber;
	
	@JsonBackReference
	@JoinColumn(name="entry_id")
	@ManyToOne
	private Entry entryConnections;
	
	@OneToMany(mappedBy = "entryConnections", cascade = CascadeType.REMOVE)
	private Set<Entry> addEntry;
	
	public Entry() {
		
	}

	public Entry(String title, String description, LocalDateTime date, LocalDateTime duration, 
			LocalDateTime termin, EntryType entryType, EntryPhase entryPhase) {
		this.addEntry = new HashSet<Entry>();
		
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.termin = termin;
		this.entryType = entryType;
		this.entryPhase = entryPhase;
		this.isChild = false;
		this.isFinished = false;
		this.isDeleted = false;
	}
	
	

	public Entry getEntryConnections() {
		return entryConnections;
	}

	public void setEntryConnections(Entry entryConnections) {
		this.entryConnections = entryConnections;
	}
	
	public void addEntryConnection(Entry entryConnection) {
		this.entryConnections = entryConnection;
	}

	public Set<Entry> getAddEntry() {
		return addEntry;
	}

	public void AddEntry(Set<Entry> addEntry) {
		this.addEntry = addEntry;
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
	
	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
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
		
}
