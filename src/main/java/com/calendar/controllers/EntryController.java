package com.calendar.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.requestdto.EntryDto;
import com.calendar.responsedto.EntryResponseDto;
import com.calendar.service.impl.EntryServiceImpl;

@RestController
@RequestMapping("/entry")
public class EntryController {
	
	private EntryServiceImpl entryServiceImpl;
	
	@Autowired
	public EntryController(EntryServiceImpl entryServiceImpl) {
		this.entryServiceImpl = entryServiceImpl;
	}

	@PostMapping("/firstentry")
	public void makeFirstEntry(@Valid @RequestBody EntryDto entryDto) {
		entryServiceImpl.makeFirstEntry(entryDto);
	}
	
	@GetMapping("/getentries")
	public EntryResponseDto getEntries() {
		
		return entryServiceImpl.getEntries();
	}
}
