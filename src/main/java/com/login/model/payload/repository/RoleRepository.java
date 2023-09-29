package com.login.model.payload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.model.ERole;
import com.login.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	  Optional<Role> findByName(ERole name);
	}

