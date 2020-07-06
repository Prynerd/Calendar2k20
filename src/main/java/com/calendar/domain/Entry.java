package com.calendar.domain;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

	private boolean isClosed;
	
	private boolean isDeleted;
	
	private Integer sortNumber;

	private boolean isExpanded;
	
	@JsonBackReference
	@JoinColumn(name="entry_id")
	@ManyToOne
	private Entry entryConnections;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OrderBy("sortNumber ASC")
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
		this.isClosed = false;
		this.isDeleted = false;
		this.isExpanded = true;
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
	    if (entryPhase == EntryPhase.COMPLETED) {
	        setClosed(true);
        }
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

	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
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

	public void setExpanded(boolean expanded) {
		this.isExpanded = expanded;
	}


}
