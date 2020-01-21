package com.calendar.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
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
	
	@JoinTable(name = "Entry_Connection", joinColumns = {
		    @JoinColumn(name = "ADDING_ENTRY", referencedColumnName = "ID", nullable =   false)}, inverseJoinColumns = {
		    @JoinColumn(name = "ADDED_Entry", referencedColumnName = "ID", nullable = false)})
	@ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
	private List<Entry> entryConnections;
	
//	@ManyToMany(mappedBy = "entryConnections",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//	private List<Entry> addEntry;
	
	public Entry() {
		
	}

	public Entry(String title, String description, LocalDateTime date, LocalDateTime duration, 
			LocalDateTime termin, EntryType entryType, EntryPhase entryPhase) {
		this.entryConnections = new ArrayList<Entry>();
//		this.addEntry = new ArrayList<Entry>();
		
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.termin = termin;
		this.entryType = entryType;
		this.entryPhase = entryPhase;
	}
	
	

	public List<Entry> getEntryConnections() {
		return entryConnections;
	}

	public void setEntryConnections(List<Entry> entryConnections) {
		this.entryConnections = entryConnections;
	}
	
	public void addEntryConnection(Entry entryConnection) {
		this.entryConnections.add(entryConnection);
	}

//	public List<Entry> getAddEntry() {
//		return addEntry;
//	}
//
//	public void AddEntry(List<Entry> addEntry) {
//		this.addEntry = addEntry;
//	}

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
	
	
}
