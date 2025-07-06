package com.example.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.dto.WishlistDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Wishlist {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public WishlistDto getWishlistDto() {
		WishlistDto wishlistDto = new WishlistDto();
		wishlistDto.setId(id);
		wishlistDto.setPrice(product.getPrice());
		wishlistDto.setProductDescription(product.getDescription());
		wishlistDto.setProductId(product.getId());
		wishlistDto.setProductName(product.getName());
		wishlistDto.setUserId(user.getId());
		wishlistDto.setReturnedImg(product.getImg());
		return wishlistDto;
	}

}
