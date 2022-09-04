package com.yannfigueiredo.petsarea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yannfigueiredo.petsarea.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
	Owner findByEmail(String email);
}
