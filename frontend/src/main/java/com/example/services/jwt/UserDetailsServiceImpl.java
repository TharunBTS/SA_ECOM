package com.example.services.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<User> optionalUser = userRepository.findFirstByEmail(username);
	    if (optionalUser.isEmpty())
	        throw new UsernameNotFoundException("Username not found");

	    User user = optionalUser.get();

	    // ✅ Create authority with ROLE_ prefix
	    String role = "ROLE_" + user.getRole().name(); // e.g., ROLE_ADMIN
	    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

	    return new org.springframework.security.core.userdetails.User(
	            user.getEmail(),
	            user.getPassword(),
	            authorities // ✅ Add authorities here
	    );
	}

}
