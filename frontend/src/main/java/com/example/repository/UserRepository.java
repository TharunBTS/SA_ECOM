package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
import com.example.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findFirstByEmail(String username);
	User findByRole(UserRole userRole);
}
