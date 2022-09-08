package com.yannfigueiredo.petsarea.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.dto.OwnerUpdateDTO;
import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.services.OwnerService;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping
	public ResponseEntity<List<OwnerDTO>> findAll() {
		List<OwnerDTO> list = ownerService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
		OwnerDTO dto = ownerService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<OwnerDTO> insert(@Valid @RequestBody OwnerInsertDTO dto) {
		OwnerDTO newDTO = ownerService.insert(dto);
		
		return ResponseEntity.ok().body(newDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OwnerDTO> update(@PathVariable Long id, @Valid @RequestBody OwnerUpdateDTO dto) {
		OwnerDTO newDTO = ownerService.update(id, dto);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		return ResponseEntity.ok().body(newDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PetDTO> delete(@PathVariable Long id) {
		ownerService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
