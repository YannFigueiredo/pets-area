package com.yannfigueiredo.petsarea.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.repositories.PetRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;
import com.yannfigueiredo.petsarea.tests.Factory;

@ExtendWith(SpringExtension.class)
public class PetServiceTests {
	@InjectMocks
	private PetService service;
	
	@Mock
	private PetRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private PageImpl<Pet> page;
	Pet pet;
	PetDTO petDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		
		pet = Factory.createPet();
		petDTO = Factory.createPetDTO();
		
		page = new PageImpl<>(List.of(pet));
		
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		Mockito.doNothing().when(repository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
	}
	@Test
	public void findAllShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<PetDTO> result = service.findAllFiltered(null, null, null, null, null) 
	}
	
	@Test
	public void deleteShouldDoNotingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowControllerNotFoundExceptionIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId);
	}
}
