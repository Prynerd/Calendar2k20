package com.calendar.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.EntryDtoForModification;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.EntryListResponseDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.responsedto.FullProjectResponseDto;
import com.calendar.responsedto.ProjectEntriesResponseDto;
import com.calendar.responsedto.ProjectViewResponseDto;

public interface EntryService {

	public ProjectViewResponseDto createEntry(EntryDto entryDto);
	
	public void createProject(ProjectDto projectDto);
	
	public ArrayList<ProjectEntriesResponseDto> getProjects(boolean isClosed);
	
	public EntryListResponseDto getEntries();
	
	public EntryResponseDto getEntryById(int id);
	
	public FullProjectResponseDto getFullProjectById(int id);
	
	public ProjectViewResponseDto deleteEntryById(int id);
	
	public ProjectViewResponseDto modifyEntryById(int id, EntryDtoForModification eDto);
	
	public ProjectViewResponseDto modifyProjectById(int id, ProjectDto projectDto);

	public void expandEntry(int id, boolean isExpanded);
	
	public ProjectViewResponseDto getProjectView(Integer id);

	public int getProjectIdOfEntry(int id);
}
