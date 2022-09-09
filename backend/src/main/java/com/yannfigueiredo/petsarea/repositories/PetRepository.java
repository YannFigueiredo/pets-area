package com.yannfigueiredo.petsarea.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yannfigueiredo.petsarea.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	@Query(nativeQuery = true, 
			value = "SELECT * FROM tb_pet WHERE "
					+ "(:type IS NULL OR type = :type) AND "
					+ "(:gender IS NULL OR gender = :gender) AND"
					+ "((:minAge IS NULL OR age >= :minAge) AND (:maxAge IS NULL OR age <= :maxAge))")
	Page<Pet> findAllFiltered(Integer minAge, Integer maxAge, Integer type, Integer gender, Pageable pageable);
	
	@Query(nativeQuery = true,
			value = "SELECT * FROM tb_pet WHERE "
					+ "name LIKE %:searchedWord% OR "
					+ "description LIKE %:searchedWord%")
	Page<Pet> search(String searchedWord, Pageable pageable);
}
