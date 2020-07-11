package com.calendar.responsedto;

import java.util.ArrayList;

public class ProjectViewResponseDto {


	private FullProjectResponseDto projectsEntries;
	private ArrayList<ProjectEntriesResponseDto> projectList;
	
	public ProjectViewResponseDto(FullProjectResponseDto projectsEntries, ArrayList<ProjectEntriesResponseDto> projectList) {
		this.projectsEntries = projectsEntries;
		this.projectList = projectList;
	}

	public ArrayList<ProjectEntriesResponseDto> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ProjectEntriesResponseDto> projectList) {
		this.projectList = projectList;
	}

	public FullProjectResponseDto getProjectsEntries() {
		return projectsEntries;
	}

	public void setProjectsEntries(FullProjectResponseDto projectsEntries) {
		this.projectsEntries = projectsEntries;
	}
}
