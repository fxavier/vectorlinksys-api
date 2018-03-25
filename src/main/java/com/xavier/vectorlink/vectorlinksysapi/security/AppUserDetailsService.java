package com.xavier.vectorlink.vectorlinksysapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> foundUser = userRepository.findByEmail(email);
		User user = foundUser.orElseThrow(() -> new UsernameNotFoundException("E-mail /ou Senha incorrectos"));
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getRoles(user));
	}

	private Collection<? extends GrantedAuthority> getRoles(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return null;
	}

	

}
