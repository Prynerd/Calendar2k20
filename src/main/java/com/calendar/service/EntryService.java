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

	public void createEntry(EntryDto entryDto);
	
	public void createProject(ProjectDto projectDto);
	
	public ArrayList<ProjectEntriesResponseDto> getProjekts(boolean isFinished);
	
	public EntryListResponseDto getEntries();
	
	public EntryResponseDto getEntryById(int id);
	
	public FullProjectResponseDto getFullProjectById(int id);
	
	public void deleteEntryById(int id);
	
	public void modifyEntryById(int id, EntryDto eDto);
	
	public void modifyProjectById(int id, ProjectDto projectDto);
	
	public ProjectviewResponseDto getProjectview(int id, boolean finished);
}
