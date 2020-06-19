package com.calendar.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import com.calendar.responsedto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.ProjectDto;
import com.calendar.service.impl.EntryServiceImpl;

@RestController
@RequestMapping
public class EntryController {
	
	private EntryServiceImpl entryServiceImpl;
	
	@Autowired
	public EntryController(EntryServiceImpl entryServiceImpl) {
		this.entryServiceImpl = entryServiceImpl;
	}
	
	@GetMapping("/entry/getentries")
	public EntryListResponseDto getEntries() {
		return entryServiceImpl.getEntries();
	}

	@PostMapping("/entry")
	public void makeFirstEntry(@Valid @RequestBody EntryDto entryDto) {
		entryServiceImpl.createEntry(entryDto);
	}
	
	@PostMapping("/project")
	public void makeProjectEntry(@Valid @RequestBody ProjectDto projectDto) {
		entryServiceImpl.createProject(projectDto);
	}
	
	@GetMapping("/projects{isClosed}")
	public ArrayList<ProjectEntriesResponseDto> getProjekts(@Valid @RequestParam boolean isClosed){
		return entryServiceImpl.getProjekts(isClosed);
	}
	
	@GetMapping("/project{id}")
	public FullProjectResponseDto getFullProjectById(@Valid @RequestParam int id) {
		return entryServiceImpl.getFullProjectById(id);
	}
	
	@GetMapping("/projectview{id, status}")
	public ProjectviewResponseDto getProjectview(@Valid @RequestParam int id, boolean status) {
		return entryServiceImpl.getProjectview(id, status);
	}
	
	@GetMapping("/entry{id}")
	public EntryResponseDto getEntryById(@RequestParam int id) {
		return entryServiceImpl.getEntryById(id);
	}
	
	@DeleteMapping("/entry{id}")
	public void deleteEntryById(@RequestParam int id) {
		entryServiceImpl.deleteEntryById(id);
	}
	
	@PutMapping("/entry{id}")
	public void ModifyEntryById(@Valid @RequestBody EntryDto eDto, @RequestParam int id) {
		entryServiceImpl.modifyEntryById(id, eDto);
	}
	
	@PutMapping("/project{id}")
	public void ModifyProjectById(@Valid @RequestBody ProjectDto projectDto, @RequestParam int id) {
		entryServiceImpl.modifyProjectById(id, projectDto);
	}

	@PostMapping("/expand{id, isExpanded}")
	public void expand(@RequestParam int id, boolean isExpanded) {
		entryServiceImpl.expandEntry(id, isExpanded);
	}
}
