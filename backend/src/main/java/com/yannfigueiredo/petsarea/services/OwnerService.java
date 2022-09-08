package com.yannfigueiredo.petsarea.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yannfigueiredo.petsarea.dto.OwnerDTO;
import com.yannfigueiredo.petsarea.dto.OwnerInsertDTO;
import com.yannfigueiredo.petsarea.dto.OwnerUpdateDTO;
import com.yannfigueiredo.petsarea.dto.RoleDTO;
import com.yannfigueiredo.petsarea.entities.Owner;
import com.yannfigueiredo.petsarea.entities.Role;
import com.yannfigueiredo.petsarea.repositories.OwnerRepository;
import com.yannfigueiredo.petsarea.repositories.RoleRepository;
import com.yannfigueiredo.petsarea.services.exceptions.ControllerNotFoundException;
import com.yannfigueiredo.petsarea.services.exceptions.DatabaseException;

@Service
public class OwnerService implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(OwnerService.class);
	
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public List<OwnerDTO> findAll() {
		List<Owner> list =  ownerRepository.findAll();
		
		return list.stream().map(x -> new OwnerDTO(x)).collect(Collectors.toList());
	}
	
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
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		entity.getRoles().clear();
		for(RoleDTO role : dto.getRoles()) {
			Role newRole = roleRepository.getOne(role.getId());
			entity.getRoles().add(newRole);
		}
		
		entity = ownerRepository.save(entity);
		
		return new OwnerDTO(entity);
	}
	
	@Transactional
	public OwnerDTO update(Long id, OwnerUpdateDTO dto) {
		try {
			Owner entity = ownerRepository.getOne(id);
			
			entity.setFirstName(dto.getFirstName());
			entity.setLastName(dto.getLastName());        
			entity.setGender(dto.getGender());
			entity.setAge(dto.getAge() == null ? entity.getAge() : dto.getAge());
			entity.setPhoto(dto.getPhoto() == null ? entity.getPhoto() : dto.getPhoto());
			entity.setEmail(dto.getEmail());
			entity.setPassword(passwordEncoder.encode(dto.getPassword()));
			
			entity.getRoles().clear();
			for(RoleDTO role : dto.getRoles()) {
				Role newRole = roleRepository.getOne(role.getId());
				entity.getRoles().add(newRole);
			}
			
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Owner owner = ownerRepository.findByEmail(username);
		
		if(owner == null) {
			logger.error("Usuário não encontrado: " + username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
			
		logger.info("Usuário encontrado: " + username);
			
		return owner;
	}
}
