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
	
	public ProjectDto() {
		
	}

	public ProjectDto(String title, String description, LocalDateTime termin) {
		this.title = title;
		this.description = description;
		this.termin = termin;
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
	
}
