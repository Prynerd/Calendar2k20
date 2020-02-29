package com.calendar.requestdto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjectDto {

	@NotNull
	private String title;

	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
	private LocalDateTime termin;

	private String entryType;

	private String entryPhase;
	
	public ProjectDto() {
		
	}

	public ProjectDto(String title, String description, LocalDateTime termin,
			String entryType, String entryPhase) {
		this.title = title;
		this.description = description;
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
