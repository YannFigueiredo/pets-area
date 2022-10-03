package com.yannfigueiredo.petsarea.tests;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.dto.OwnerUpdateDTO;
import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.entities.enums.Gender;
import com.yannfigueiredo.petsarea.entities.enums.Type;

public class Factory {
	public static Pet createPet() {
		Pet pet = new Pet(1L, "Lion", 2, Gender.M, Type.Dog,"Pastor alemão SRD.", "");
		pet.setOwner(new Owner(1l, "Yann", "Figueiredo", 27, Gender.M, "", "yann@gmail.com", "123"));
		
		return pet;
	}
	
	public static PetDTO createPetDTO() {
		Pet pet = createPet();
		
		return new PetDTO(pet);
	}
	
	public static Owner createOwner() {
		Owner owner = new Owner(1L, "Bob", "Souza", 33, Gender.M, "", "bob@gmail.com", "teste123");
		
		return owner;
	}
	
	public static OwnerDTO createOwnerDTO() {
		Owner owner = createOwner();
		
		return new OwnerDTO(owner);
	}
	
	public static OwnerUpdateDTO createOwnerUpdateDTO() {
		OwnerUpdateDTO owner = new OwnerUpdateDTO();
		
		owner.setFirstName("Son");
		owner.setLastName("Goku");        
		owner.setGender(Gender.M);
		owner.setAge(40);
		owner.setPhoto("");
		owner.setEmail("goku@gmail.com");
		owner.setPassword("teste123");
		
		return owner;
	}
	
	public static OwnerInsertDTO createOwnerInsertDTO() {
		OwnerInsertDTO owner = new OwnerInsertDTO();
		
		owner.setFirstName("Son");
		owner.setLastName("Goku");        
		owner.setGender(Gender.M);
		owner.setAge(40);
		owner.setPhoto("");
		owner.setEmail("goku@gmail.com");
		owner.setPassword("teste123");
		
		return owner;
	}
}
