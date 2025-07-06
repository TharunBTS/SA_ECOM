package com.example.services.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dto.SignupRequest;
import com.example.dto.UserDto;
import com.example.entity.Order;
import com.example.entity.User;
import com.example.enums.OrderStatus;
import com.example.enums.UserRole;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

import jakarta.annotation.PostConstruct;



@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public UserDto createUser(SignupRequest signupRequest)
	{
		User user = new User();
		user.setName(signupRequest.getName());
		user.setEmail(signupRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.CUSTOMER);
		User createdUser = userRepository.save(user);
		UserDto userdto = new UserDto();
		userdto.setId(createdUser.getId());
		
		Order order = new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		return userdto;
	}
	
	@Override
	public boolean hasUserWithEmail(String email) {
	    Optional<User> user = userRepository.findFirstByEmail(email);
	    System.out.println("Checking email: " + email + " | Found: " + user.isPresent());
	    return user.isPresent();
	}
	
	@PostConstruct
	public void createAdminAccount()
	{
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if(adminAccount == null)
		{
			User user = new User();
			user.setName("admin");
			user.setEmail("admin@test.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(UserRole.ADMIN);
			userRepository.save(user);
		}
	}
	
}
