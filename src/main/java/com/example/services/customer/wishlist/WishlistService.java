package com.example.services.customer.wishlist;

import java.util.List;

import com.example.dto.WishlistDto;

public interface WishlistService {
	
	
	WishlistDto addProductToWishlist(WishlistDto wishlistDto);
	List<WishlistDto> getWishlistByUserId(Long userId);
}
