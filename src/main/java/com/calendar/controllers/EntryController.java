package com.calendar.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

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
import com.calendar.responsedto.EntryListResponseDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.responsedto.FullProjectResponseDto;
import com.calendar.responsedto.ProjectEntriesResponseDto;
import com.calendar.responsedto.ProjectviewResponseDto;
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
	public ProjectviewResponseDto addEntryOnProjectview(@Valid @RequestBody EntryDto entryDto) {
		return entryServiceImpl.createEntry(entryDto);
	}
	
	@PostMapping("/project")
	public void makeProjectEntry(@Valid @RequestBody ProjectDto projectDto) {
		entryServiceImpl.createProject(projectDto);
	}
	
	@GetMapping("/projects{openOnly}")
	public ArrayList<ProjectEntriesResponseDto> getProjects(@Valid @RequestParam boolean openOnly){
		return entryServiceImpl.getProjects(openOnly);
	}
	
	@GetMapping("/project{id}")
	public FullProjectResponseDto getFullProjectById(@Valid @RequestParam int id) {
		return entryServiceImpl.getFullProjectById(id);
	}
	
	@GetMapping("/projectview{id}")
	public ProjectviewResponseDto getProjectview(@Valid @RequestParam int id) {
		return entryServiceImpl.getProjectview(id);
	}
	
	@GetMapping("/entry{id}")
	public EntryResponseDto getEntryById(@RequestParam int id) {
		return entryServiceImpl.getEntryById(id);
	}
	
	@DeleteMapping("/entry{id}")
	public ProjectviewResponseDto deleteEntryById(@RequestParam int id) {
		return entryServiceImpl.deleteEntryById(id);
	}
	
	@PutMapping("/entry{id}")
	public ProjectviewResponseDto ModifyEntryById(@Valid @RequestBody EntryDto eDto, @RequestParam int id) {
		return entryServiceImpl.modifyEntryById(id, eDto);
	}
	
	@PutMapping("/project{id}")
	public ProjectviewResponseDto ModifyProjectById(@Valid @RequestBody ProjectDto projectDto, @RequestParam int id) {
		return entryServiceImpl.modifyProjectById(id, projectDto);
	}

	@PostMapping("/expand{id, isExpanded}")
	public void expand(@RequestParam int id, boolean isExpanded) {
		entryServiceImpl.expandEntry(id, isExpanded);
	}

	@GetMapping("/parents")
	public String getParents(@RequestParam int id) throws Exception {
		return entryServiceImpl.getParents(id);
	}
}
