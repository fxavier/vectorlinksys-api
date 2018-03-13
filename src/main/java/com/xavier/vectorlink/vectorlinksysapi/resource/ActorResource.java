package com.xavier.vectorlink.vectorlinksysapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.xavier.vectorlink.vectorlinksysapi.model.Actor;
import com.xavier.vectorlink.vectorlinksysapi.repository.ActorRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.ActorService;

@RestController
@RequestMapping("/actors")
public class ActorResource {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Actor> create(@Valid @RequestBody Actor actor, HttpServletResponse response){
		Actor savedActor = actorService.save(actor);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedActor.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedActor);
		
	}
	
	@GetMapping
	public List<Actor> search(){
		return actorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> findById(@PathVariable Long id){
		Actor foundActor = actorRepository.getOne(id);
		return foundActor != null ? ResponseEntity.ok(foundActor) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Actor> edit(@PathVariable Long id, @Valid @RequestBody Actor actor){
		Actor savedActor = actorService.update(id, actor);
		return ResponseEntity.ok(savedActor);
	}
	

}
