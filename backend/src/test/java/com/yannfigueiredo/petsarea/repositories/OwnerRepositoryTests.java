package com.yannfigueiredo.petsarea.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.tests.Factory;

@DataJpaTest
public class OwnerRepositoryTests {
	@Autowired
	private OwnerRepository repository;
	
	private long existingId;
	private long nonExistingId;
	private long countOwners;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 9999L;
		
		countOwners = 3L;
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		Owner owner = Factory.createOwner();
		owner.setId(null);
		
		owner = repository.save(owner);
		
		Assertions.assertNotNull(owner.getId());
		Assertions.assertEquals(owner.getId(), countOwners + 1);
	}
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		repository.deleteById(existingId);
		
		Optional<Owner> result = repository.findById(existingId);
		
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
		Optional<Owner> owner = repository.findById(existingId);
		Assertions.assertTrue(owner.isPresent());
	}
	
	@Test 
	public void findByIdSholdFindObjectOptionalIsVoidWhenDoesNotExistId() {
		Optional<Owner> owner = repository.findById(nonExistingId);
		Assertions.assertTrue(owner.isEmpty());
	}
}
