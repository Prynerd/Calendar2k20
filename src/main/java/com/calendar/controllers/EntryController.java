package com.calendar.controllers;

import com.calendar.requestdto.EntryDto;
import com.calendar.requestdto.EntryDtoForModification;
import com.calendar.requestdto.ProjectDto;
import com.calendar.responsedto.*;
import com.calendar.service.impl.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
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
	public ProjectViewResponseDto addEntryOnProjectview(@Valid @RequestBody EntryDto entryDto) {
		return entryServiceImpl.createEntry(entryDto);
	}
	
	@PostMapping("/project")
	public ProjectViewResponseDto makeProjectEntry(@Valid @RequestBody ProjectDto projectDto) {
		return entryServiceImpl.createProject(projectDto);
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
	public ProjectViewResponseDto getProjectview(@Valid @RequestParam int id) {
		return entryServiceImpl.getProjectView(id);
	}
	
	@GetMapping("/entry{id}")
	public EntryResponseDto getEntryById(@RequestParam int id) {
		return entryServiceImpl.getEntryById(id);
	}
	
	@DeleteMapping("/entry{id}")
	public ProjectViewResponseDto deleteEntryById(@RequestParam int id) {
		return entryServiceImpl.deleteEntryById(id);
	}
	
	@PutMapping("/entry{id}")
	public ProjectViewResponseDto modifyEntryById(@Valid @RequestBody EntryDtoForModification eDto, @RequestParam int id) {
		return entryServiceImpl.modifyEntryById(id, eDto);
	}
	
	@PutMapping("/project{id}")
	public ProjectViewResponseDto modifyProjectById(@Valid @RequestBody ProjectDto projectDto, @RequestParam int id) {
		return entryServiceImpl.modifyProjectById(id, projectDto);
	}

	@PostMapping("/expand{id, isExpanded}")
	public void expand(@RequestParam int id, boolean isExpanded) {
		entryServiceImpl.expandEntry(id, isExpanded);
	}
}
