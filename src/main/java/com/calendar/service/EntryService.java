package com.calendar.service;

import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.EntryForModificationDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.*;

import java.util.ArrayList;

public interface EntryService {

	ProjectViewResponseDto createEntry(EntryDto entryDto);
	
	ProjectViewResponseDto createProject(ProjectDto projectDto);
	
	ArrayList<ProjectEntriesResponseDto> getProjects();
	
	EntryListResponseDto getEntries();
	
	EntryResponseDto getEntryById(int id);
	
	FullProjectResponseDto getFullProjectById(int id);
	
	ProjectViewResponseDto deleteEntryById(int id);
	
	ProjectViewResponseForModificationDto modifyEntryById(int id, EntryForModificationDto eDto,
														  boolean checkIfAllChildrenAreClosed,
														  boolean checkIfAllSiblingsAreClosed);
	
	ProjectViewResponseDto modifyProjectById(int id, ProjectDto projectDto);

	void expandEntry(int id, boolean isExpanded);
	
	ProjectViewResponseDto getProjectView(Integer id);


}
