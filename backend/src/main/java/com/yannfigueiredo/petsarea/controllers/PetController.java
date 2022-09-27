package com.yannfigueiredo.petsarea.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.services.PetService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/pets")
public class PetController {
	@Autowired
	private PetService petService;
	
	@GetMapping(value = "/search")
	@ApiOperation(value = "Retorna uma lista paginada de pets filtrados por busca de string")
	public ResponseEntity<Page<PetDTO>> search(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "20") Integer size,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "searchedWord", defaultValue = "") String searchedWord
			) {
		PageRequest pageRequest = PageRequest.of(page, 
				size, 
				Direction.valueOf(direction),
				"id"
				);
		
		Page<PetDTO> list = petService.search(searchedWord, pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	@ApiOperation(value = "Retorna uma lista paginada de todos os pets e filtra a lista")
	public ResponseEntity<Page<PetDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "20") Integer size,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "gender", defaultValue = "") String gender,
			@RequestParam(value = "minAge", defaultValue = "") String minAge,
			@RequestParam(value = "maxAge", defaultValue = "") String maxAge
			) {
		PageRequest pageRequest = PageRequest.of(page, 
				size, 
				Direction.valueOf(direction), 
				orderBy);
		
		Integer petType = type.equals("") ? null : Integer.parseInt(type);
		Integer petGender = gender.equals("") ? null : Integer.parseInt(gender);
		Integer min = minAge.equals("") ? null : Integer.parseInt(minAge);
		Integer max = maxAge.equals("") ? null : Integer.parseInt(maxAge);
		
		Page<PetDTO> list = petService.findAllFiltered(min, max, petType, petGender, pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Resource not found")
	})
	@ApiOperation(value = "Retorna um pet e suas informações")
	public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
		PetDTO dto = petService.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@ApiResponses(value = {
			@ApiResponse(code = 422, message = "Validation exception")
	})
	@ApiOperation(value = "Adiciona um novo pet")
	public ResponseEntity<PetDTO> insert(@Valid @RequestBody PetDTO dto) {
		PetDTO newDTO = petService.insert(dto);
		
		return ResponseEntity.ok().body(newDTO);
	}
	
	@PutMapping(value = "/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Resource not found"),
			@ApiResponse(code = 422, message = "Validation exception")
	})
	@ApiOperation(value = "Atualiza um pet existente")
	public ResponseEntity<PetDTO> update(@PathVariable Long id, @Valid @RequestBody PetDTO dto) {
		dto = petService.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Resource not found")
	})
	@ApiOperation(value = "Deleta um pet existente")
	public ResponseEntity<PetDTO> delete(@PathVariable Long id) {
		petService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
