package com.example.dto;



import org.springframework.web.multipart.MultipartFile;



public class ReviewDto {
	
	
	private Long id;
	
	private Long rating;
	
	
	private String description;
	
	
	private byte[] returnedimg;
	
	private MultipartFile img;
	
	
	private Long userId;
	
	
	private Long productId;
	
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public byte[] getReturnedimg() {
		return returnedimg;
	}

	public void setReturnedimg(byte[] returnedimg) {
		this.returnedimg = returnedimg;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
