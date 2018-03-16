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
import com.xavier.vectorlink.vectorlinksysapi.model.Mobilizer;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizerRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.MobilizerService;

@RestController
@RequestMapping("/mobilizers")
public class MobilizerResource {
	
	@Autowired
	private MobilizerRepository mobilizerRepository;
	
	@Autowired
	private MobilizerService mobilizerService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	public ResponseEntity<Mobilizer> create(@Valid @RequestBody Mobilizer mobilizer, HttpServletResponse response) {
		Mobilizer savedMobilizer = mobilizerService.save(mobilizer);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedMobilizer.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMobilizer);
	}
	
	@GetMapping
	public List<Mobilizer> search(){
		return mobilizerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mobilizer> findById(@PathVariable Long id){
		Mobilizer mobilizer = mobilizerRepository.getOne(id);
		return mobilizer != null ? ResponseEntity.ok(mobilizer) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Mobilizer> edit(@PathVariable Long id, @Valid @RequestBody Mobilizer mobilizer){
		Mobilizer updatedMobilizer = mobilizerService.update(id, mobilizer);
		return ResponseEntity.ok(updatedMobilizer);
	}

}
