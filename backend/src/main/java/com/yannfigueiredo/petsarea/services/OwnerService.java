package com.yannfigueiredo.petsarea.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;

@Service
public class OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Transactional(readOnly = true)
	public OwnerDTO findById(Long id) {
		Optional<Owner> obj =  ownerRepository.findById(id);
		Owner entity = obj.get();
		
		return new OwnerDTO(entity);
	}
}
