package com.yannfigueiredo.petsarea.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.repositories.PetRepository;

@Service
public class PetService {
	@Autowired
	private PetRepository petRepository;
	
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
}
