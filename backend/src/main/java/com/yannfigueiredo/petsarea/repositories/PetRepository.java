package com.yannfigueiredo.petsarea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yannfigueiredo.petsarea.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
