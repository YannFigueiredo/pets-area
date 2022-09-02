package com.yannfigueiredo.petsarea.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;
import com.yannfigueiredo.petsarea.services.exceptions.DatabaseException;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Transactional(readOnly = true)
	public OwnerDTO findById(Long id) {
		Optional<Owner> obj =  ownerRepository.findById(id);
		Owner entity = obj.orElseThrow(() -> new ControllerNotFoundException("Entidade não encontrada"));
		
		return new OwnerDTO(entity);
	}
	
	@Transactional
	public OwnerDTO insert(OwnerInsertDTO dto) {
		Owner entity = new Owner();
		
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setAge(dto.getAge());
		entity.setGender(dto.getGender());
		entity.setPhoto(dto.getPhoto());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPasssword());
		
		entity = ownerRepository.save(entity);
		
		return new OwnerDTO(entity);
	}
	
	@Transactional
	public OwnerDTO update(Long id, OwnerDTO dto) {
		try {
			Owner entity = ownerRepository.getReferenceById(id);
			
			entity.setFirstName(dto.getFirstName() == null ? entity.getFirstName() : dto.getFirstName());
			entity.setLastName(dto.getLastName() == null ? entity.getLastName() : dto.getLastName());        
			entity.setGender(dto.getGender() == null ? entity.getGender() : dto.getGender());
			entity.setAge(dto.getAge() == null ? entity.getAge() : dto.getAge());
			entity.setPhoto(dto.getPhoto() == null ? entity.getPhoto() : dto.getPhoto());
			
			entity = ownerRepository.save(entity);
			
			return new OwnerDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ControllerNotFoundException("O ID " + id + " não foi encontrado");
		}
	}
	
	public void delete(Long id) {
		try {
			ownerRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("O ID " + id + " não foi encontrado");
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação da integridade do banco de dados");
		}
	}
}
