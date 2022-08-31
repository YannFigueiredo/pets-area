package com.yannfigueiredo.petsarea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.dto.PetInsertDTO;
import com.yannfigueiredo.petsarea.services.PetService;

@RestController
@RequestMapping(value = "/pets")
public class PetController {
	@Autowired
	private PetService petService;
	
	@GetMapping
	public ResponseEntity<Page<PetDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "20") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction
			) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<PetDTO> list = petService.findAll(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
		PetDTO dto = petService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<PetDTO> insert(@RequestBody PetInsertDTO dto) {
		PetDTO newDTO = petService.insert(dto);
		
		return ResponseEntity.ok().body(newDTO);
	}
}
