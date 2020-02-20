package com.calendar.requestdto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

public class ProjectDto {

	@NotNull
	private String title;

	private String description;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime date;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime duration;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime termin;

	@NotNull
	private String entryType;

	@NotNull
	private String entryPhase;
	
	public ProjectDto() {
		
	}

	public ProjectDto(String title, String description, LocalDateTime date, LocalDateTime duration, LocalDateTime termin,
			String entryType, String entryPhase) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.duration = duration;
		this.termin = termin;
		this.entryType = entryType;
		this.entryPhase = entryPhase;
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

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEntryPhase() {
		return entryPhase;
	}

	public void setEntryPhase(String entryPhase) {
		this.entryPhase = entryPhase;
	}
}
