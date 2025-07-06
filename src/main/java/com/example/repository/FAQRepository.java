package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.FAQ;


@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long>{
	List<FAQ> findAllByProduct_Id(Long productId);
}
