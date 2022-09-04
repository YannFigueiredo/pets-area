package com.yannfigueiredo.petsarea.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.PetDTO;
import com.yannfigueiredo.petsarea.entities.Pet;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.repositories.PetRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;

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
		Pet entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entidade não encontrada"));
		
		return new PetDTO(entity);
	}
	
	@Transactional
	public PetDTO insert(PetDTO dto) {
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
	
	@Transactional
	public PetDTO update(Long id, PetDTO dto) {
		try {
			Pet entity = petRepository.getReferenceById(id);
			
			entity.setName(dto.getName() == null ? entity.getName() : dto.getName());
			entity.setDescription(dto.getDescription() == null ? entity.getDescription() : dto.getDescription());         
			entity.setGender(dto.getGender() == null ? entity.getGender() : dto.getGender());
			entity.setType(dto.getType() == null ? entity.getType() : dto.getType());
			entity.setAge(dto.getAge() == null ? entity.getAge() : dto.getAge());
			entity.setPhoto(dto.getPhoto() == null ? entity.getPhoto() : dto.getPhoto());
			entity.setOwner(ownerRepository.getReferenceById(dto.getOwnerId() == null ? entity.getOwner().getId() : dto.getOwnerId()));
			
			entity = petRepository.save(entity);
			
			return new PetDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ControllerNotFoundException("O ID " + id + " não foi encontrado");
		}
	}
	
	public void delete(Long id) {
		try {
			petRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("O ID " + id + " não foi encontrado");
		}
	}
}
