package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	
	List<Wishlist> findAllByUserId(Long userId); 
	
}
