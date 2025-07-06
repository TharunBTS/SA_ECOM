package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{
	Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);
	  List<CartItems> findByOrderId(Long orderId);
}
