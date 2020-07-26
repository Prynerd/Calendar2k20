package com.calendar.responsedto;

import java.util.ArrayList;

public class ProjectViewResponseForModificationDto {


	private FullProjectResponseDto projectsEntries;
	private ArrayList<ProjectEntriesResponseDto> projectList;
	private Boolean hasNotClosedChildren;

	public ProjectViewResponseForModificationDto(FullProjectResponseDto projectsEntries,
												 ArrayList<ProjectEntriesResponseDto> projectList,
												 Boolean hasNotClosedChildren) {
		this.projectsEntries = projectsEntries;
		this.projectList = projectList;
		this.hasNotClosedChildren = hasNotClosedChildren;
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

	public Boolean isHasNotClosedChildren() {
		return hasNotClosedChildren;
	}

	public void setHasNotClosedChildren(Boolean hasNotClosedChildren) {
		this.hasNotClosedChildren = hasNotClosedChildren;
	}
}
