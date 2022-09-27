package com.yannfigueiredo.petsarea.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.tests.Factory;

@DataJpaTest
public class PetRepositoryTests {
	@Autowired
	private PetRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countPets;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		
		countPets = 4L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Pet pet = Factory.createPet();
		pet.setId(null);
		
		pet = repository.save(pet);
		
		Assertions.assertNotNull(pet.getId());
		Assertions.assertEquals(pet.getId(), countPets + 1);
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		
		Optional<Pet> result = repository.findById(existingId);
		
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {			
			repository.deleteById(nonExistingId);
		});
	}
	
	@Test 
	public void findByIdSholdFindObjectOptionalNotVoidWhenIdExists() {
		Optional<Pet> pet = repository.findById(existingId);
		Assertions.assertTrue(pet.isPresent());
	}
	
	@Test 
	public void findByIdSholdFindObjectOptionalIsVoidWhenDoesNotExistId() {
		Optional<Pet> pet = repository.findById(nonExistingId);
		Assertions.assertTrue(pet.isEmpty());
	}
}
