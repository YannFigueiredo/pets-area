package com.yannfigueiredo.petsarea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yannfigueiredo.petsarea.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
