package com.xavier.vectorlink.vectorlinksysapi.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.vectorlink.vectorlinksysapi.event.CreatedResourceEvent;
import com.xavier.vectorlink.vectorlinksysapi.model.User;
import com.xavier.vectorlink.vectorlinksysapi.repository.UserRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.UserFilter;
import com.xavier.vectorlink.vectorlinksysapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody User user, HttpServletResponse response){
		User savedUser = userService.save(user);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedUser.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	
	@GetMapping
	public Page<User> search(UserFilter userFilter, Pageable pageable){
		return userRepository.filter(userFilter, pageable);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> edit(@PathVariable Long id, @Valid @RequestBody User user){
		User updatedUser = userService.update(id, user);
		return ResponseEntity.ok(updatedUser);
	}

}
