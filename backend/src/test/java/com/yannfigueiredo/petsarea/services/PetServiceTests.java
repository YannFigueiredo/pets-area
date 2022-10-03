package com.yannfigueiredo.petsarea.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.repositories.PetRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;
import com.yannfigueiredo.petsarea.tests.Factory;

@ExtendWith(SpringExtension.class)
public class PetServiceTests {
	@InjectMocks
	private PetService service;
	
	@Mock
	private PetRepository petRepository;
	
	@Mock
	private OwnerRepository ownerRepository;
	
	private long existingId;
	private long nonExistingId;
	private PageImpl<Pet> page;
	Pet pet;
	Owner owner;
	PetDTO petDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		
		pet = Factory.createPet();
		petDTO = Factory.createPetDTO();
		
		owner = Factory.createOwner();
		
		page = new PageImpl<>(List.of(pet));
		
		Mockito.when(petRepository.search(
				ArgumentMatchers.any(), 
				(PageRequest) ArgumentMatchers.any()))
		.thenReturn(page);
		
		Mockito.when(petRepository.findAllFiltered(
				ArgumentMatchers.any(), 
				ArgumentMatchers.any(), 
				ArgumentMatchers.any(), 
				ArgumentMatchers.any(), 
				(PageRequest) ArgumentMatchers.any()))
		.thenReturn(page);
		
		Mockito.when(petRepository.findById(existingId)).thenReturn(Optional.of(pet));
		Mockito.when(petRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(petRepository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(petRepository).deleteById(nonExistingId);
		
		Mockito.when(petRepository.getOne(existingId)).thenReturn(pet);
		Mockito.when(petRepository.getOne(nonExistingId)).thenThrow(ControllerNotFoundException.class);
		Mockito.when(ownerRepository.getOne(existingId)).thenReturn(owner);
		Mockito.when(ownerRepository.getOne(nonExistingId)).thenThrow(ControllerNotFoundException.class);
		Mockito.when(petRepository.save(ArgumentMatchers.any())).thenReturn(pet);
	}
	
	@Test
	public void searchShouldReturnPage() {
		PageRequest pageRequest = PageRequest.of(0, 20, Direction.valueOf("DESC"), "id");
		
		Page<PetDTO> result = service.search(null, pageRequest); 
		
		Assertions.assertFalse(result.isEmpty());
		Mockito.verify(petRepository, Mockito.times(1)).search(null, pageRequest);
	}
	
	@Test
	public void findAllfilteredShouldReturnPage() {
		PageRequest pageRequest = PageRequest.of(0, 20, Direction.valueOf("DESC"), "id");
		
		Page<PetDTO> result = service.findAllFiltered(null, null, null, null, pageRequest); 
		
		Assertions.assertFalse(result.isEmpty());
		Mockito.verify(petRepository, Mockito.times(1)).findAllFiltered(null, null, null, null, pageRequest);
	}
	
	@Test
	public void findByIdShouldReturnPetDtoWhenIdExists() {
		petDTO = service.findById(existingId);
		
		Assertions.assertNotNull(petDTO);
	}
	
	@Test
	public void findByIdShouldReturnPetDtoInvalidIdWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void deleteShouldDoNotingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(petRepository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowControllerNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
		Mockito.verify(petRepository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void insertShouldSavePet() {
		PetDTO petAux = service.insert(petDTO);
		
		Assertions.assertNotNull(petAux);
		
		//Mockito.verify(petRepository).save(pet);
	}
	
	
	@Test
	public void updateShouldReturnPetDtoWhenIdExists() {
		PetDTO petAux = service.update(existingId, petDTO);
		
		Assertions.assertNotNull(petAux);
		
		//Mockito.verify(petRepository).save(pet);
		Mockito.verify(petRepository).getOne(existingId);
		Mockito.verify(ownerRepository).getOne(existingId);
	}
	
	@Test
	public void updateShouldThrowControllerNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.update(nonExistingId, petDTO);
		});
		
		Mockito.verify(petRepository).getOne(nonExistingId);
	}
}
