package com.calendar.service;

import java.util.ArrayList;

import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.EntryListResponseDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.responsedto.FullProjectResponseDto;
import com.calendar.responsedto.ProjectEntriesResponseDto;
import com.calendar.responsedto.ProjectviewResponseDto;

public interface EntryService {

	public ProjectviewResponseDto createEntry(EntryDto entryDto);
	
	public void createProject(ProjectDto projectDto);
	
	public ArrayList<ProjectEntriesResponseDto> getProjekts(boolean isFinished);
	
	public EntryListResponseDto getEntries();
	
	public EntryResponseDto getEntryById(int id);
	
	public FullProjectResponseDto getFullProjectById(int id);
	
	public ProjectviewResponseDto deleteEntryById(int id);
	
	public ProjectviewResponseDto modifyEntryById(int id, EntryDto eDto);
	
	public ProjectviewResponseDto modifyProjectById(int id, ProjectDto projectDto);

	public void expandEntry(int id, boolean isExpanded);
	
	public ProjectviewResponseDto getProjectview(Integer id);
}
