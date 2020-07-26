package com.calendar.service;

import java.util.ArrayList;
import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.EntryForModificationDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.*;

public interface EntryService {

	public ProjectViewResponseDto createEntry(EntryDto entryDto);
	
	public ProjectViewResponseDto createProject(ProjectDto projectDto);
	
	public ArrayList<ProjectEntriesResponseDto> getProjects(boolean isClosed);
	
	public EntryListResponseDto getEntries();
	
	public EntryResponseDto getEntryById(int id);
	
	public FullProjectResponseDto getFullProjectById(int id);
	
	public ProjectViewResponseDto deleteEntryById(int id);
	
	public ProjectViewResponseForModificationDto modifyEntryById(int id, EntryForModificationDto eDto,
																 boolean checkIfAllChildrenAreClosed);
	
	public ProjectViewResponseDto modifyProjectById(int id, ProjectDto projectDto);

	public void expandEntry(int id, boolean isExpanded);
	
	public ProjectViewResponseDto getProjectView(Integer id);


}
