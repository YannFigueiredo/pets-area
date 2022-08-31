package com.yannfigueiredo.petsarea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.services.OwnerService;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
		OwnerDTO dto = ownerService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<OwnerDTO> insert(@RequestBody OwnerInsertDTO dto) {
		OwnerDTO newDTO = ownerService.insert(dto);
		
		return ResponseEntity.ok().body(newDTO);
	}
	
}
