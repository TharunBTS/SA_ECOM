package com.example.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Order;
import com.example.enums.OrderStatus;



@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
	
	List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
	
	List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);
	
	Optional<Order> findByTrackingId(UUID trackingId);

	
	List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus orderStatus);
	
	Long countByOrderStatus(OrderStatus status);

}
