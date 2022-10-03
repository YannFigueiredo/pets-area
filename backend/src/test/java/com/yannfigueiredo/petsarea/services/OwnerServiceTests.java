package com.yannfigueiredo.petsarea.services;

import java.util.ArrayList;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.dto.OwnerUpdateDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;
import com.yannfigueiredo.petsarea.tests.Factory;

@ExtendWith(SpringExtension.class)
public class OwnerServiceTests {
	@InjectMocks
	private OwnerService service;
	
	@Mock
	private OwnerRepository ownerRepository;
	
	@Mock
    BCryptPasswordEncoder passwordEncoder;
	
	private long integrityViolation;
	private long existingId;
	private long nonExistingId;
	private List<Owner> list;
	
	Owner owner;
	OwnerDTO ownerDTO;
	OwnerUpdateDTO ownerUpdateDTO;
	OwnerInsertDTO ownerInsertDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		integrityViolation = 1L;
		existingId = 3L;
		nonExistingId = 9999L;
		
		owner = Factory.createOwner();
		ownerDTO = Factory.createOwnerDTO();
		ownerUpdateDTO = Factory.createOwnerUpdateDTO();
		ownerInsertDTO = Factory.createOwnerInsertDTO();
		
		owner = Factory.createOwner();
		
		list = new ArrayList<Owner>(List.of(owner));
		
		Mockito.when(ownerRepository.findAll()).thenReturn(list);
		
		Mockito.when(ownerRepository.findById(existingId)).thenReturn(Optional.of(owner));
		Mockito.when(ownerRepository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(ownerRepository).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(ownerRepository).deleteById(nonExistingId);
		Mockito.doThrow(DataIntegrityViolationException.class).when(ownerRepository).deleteById(integrityViolation);
		
		Mockito.when(ownerRepository.getOne(existingId)).thenReturn(owner);
		Mockito.when(ownerRepository.getOne(nonExistingId)).thenThrow(ControllerNotFoundException.class);
		Mockito.when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(owner);
	}
	
	@Test
	public void findAlldReturnList() {
		List<OwnerDTO> result = service.findAll(); 
		
		Assertions.assertFalse(result.isEmpty());
		Mockito.verify(ownerRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void findByIdShouldReturnOwnerDtoWhenIdExists() {
		ownerDTO = service.findById(existingId);
		
		Assertions.assertNotNull(ownerDTO);
	}
	
	@Test
	public void findByIdShouldReturnOwnerDtoInvalidIdWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void deleteShouldDoNotingWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
		
		Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(existingId);
	}
	
	@Test
	public void deleteShouldThrowControllerNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
		Mockito.verify(ownerRepository, Mockito.times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void insertShouldSaveOwner() {
		OwnerDTO ownerAux = service.insert(ownerInsertDTO);
		
		Assertions.assertNotNull(ownerAux);
		
		//Mockito.verify(ownerRepository).save(owner);
	}
	
	@Test
	public void updateShouldReturnOwnerDtoWhenIdExists() {
		OwnerDTO ownerAux = service.update(existingId, ownerUpdateDTO);
		
		Assertions.assertNotNull(ownerAux);
		
		//Mockito.verify(ownerRepository).save(owner);
		Mockito.verify(ownerRepository).getOne(existingId);
		Mockito.verify(ownerRepository).getOne(existingId);
	}
	
	@Test
	public void updateShouldThrowControllerNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ControllerNotFoundException.class, () -> {
			service.update(nonExistingId, ownerUpdateDTO);
		});
		
		Mockito.verify(ownerRepository).getOne(nonExistingId);
	}
}
