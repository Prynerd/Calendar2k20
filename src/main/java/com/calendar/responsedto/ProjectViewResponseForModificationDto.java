package com.calendar.responsedto;

import java.util.ArrayList;

public class ProjectViewResponseForModificationDto {


	private FullProjectResponseDto projectsEntries;
	private ArrayList<ProjectEntriesResponseDto> projectList;
	private Boolean hasNotClosedChildren;
	private Boolean allSiblingsAreClosed;

	public ProjectViewResponseForModificationDto(FullProjectResponseDto projectsEntries,
												 ArrayList<ProjectEntriesResponseDto> projectList,
												 Boolean hasNotClosedChildren, Boolean allSiblingsAreClosed) {
		this.projectsEntries = projectsEntries;
		this.projectList = projectList;
		this.hasNotClosedChildren = hasNotClosedChildren;
		this.allSiblingsAreClosed = allSiblingsAreClosed;
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

	public Boolean getHasNotClosedChildren() {
		return hasNotClosedChildren;
	}

	public void setHasNotClosedChildren(Boolean hasNotClosedChildren) {
		this.hasNotClosedChildren = hasNotClosedChildren;
	}

	public Boolean getAllSiblingsAreClosed() {
		return allSiblingsAreClosed;
	}

	public void setAllSiblingsAreClosed(Boolean allSiblingsAreClosed) {
		this.allSiblingsAreClosed = allSiblingsAreClosed;
	}
}
