package com.yannfigueiredo.petsarea.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.dto.PetInsertDTO;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.repositories.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Transactional(readOnly = true)
	public Page<PetDTO> findAll(PageRequest pageRequest) {
		return petRepository.findAll(pageRequest).map(x -> new PetDTO(x));
	}
	
	@Transactional(readOnly = true)
	public PetDTO findById(Long id) {
		Optional<Pet> obj = petRepository.findById(id);
		Pet entity = obj.get();
		
		return new PetDTO(entity);
	}
	
	@Transactional
	public PetDTO insert(PetInsertDTO dto) {
		Pet entity = new Pet();
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setGender(dto.getGender());
		entity.setType(dto.getType());
		entity.setAge(dto.getAge());
		entity.setPhoto(dto.getPhoto());
		entity.setOwner(ownerRepository.getReferenceById(dto.getOwnerId()));
		
		entity = petRepository.save(entity);
		
		return new PetDTO(entity);
	}
}
