package com.calendar.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.calendar.data.enums.EntryPhase;
import com.calendar.data.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User user;
	
	@NotNull
	private String title;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime date;

	@JsonFormat(pattern = "HH:mm")
	private LocalDateTime duration;

	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime termin;

	@Enumerated(EnumType.STRING)
	private EntryType entryType;

	@Enumerated(EnumType.STRING)
	private EntryPhase entryPhase;
	
	private int placeInHierarchy;
	
	public Entry() {
		
	}

	public Entry(String title, String description, LocalDateTime date, LocalDateTime duration, 
			LocalDateTime termin, EntryType entryType, EntryPhase entryPhase, int placeInHierarchy) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.termin = termin;
		this.entryType = entryType;
		this.entryPhase = entryPhase;
		this.placeInHierarchy = placeInHierarchy;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPlaceInHierarchy() {
		return placeInHierarchy;
	}

	public void setPlaceInHierarchy(int placeInHierarchy) {
		this.placeInHierarchy = placeInHierarchy;
	}
	
	
}
