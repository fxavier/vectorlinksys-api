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
import com.xavier.vectorlink.vectorlinksysapi.model.SprayOperator;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayOperatorRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.SprayOperatorService;

@RestController
@RequestMapping("/sprayOperators")
public class SprayOperatorResource {
	
	@Autowired
	private SprayOperatorService sprayOperatorService;
	
	@Autowired
	private SprayOperatorRepository sprayOperatorRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<SprayOperator> create(@Valid @RequestBody SprayOperator sprayOperator, HttpServletResponse response){
		SprayOperator savedSprayOperator = sprayOperatorService.save(sprayOperator);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedSprayOperator.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSprayOperator);
	}
	
	@GetMapping
	public List<SprayOperator> search(){
		return sprayOperatorRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SprayOperator> findById(@PathVariable Long id){
		SprayOperator foundSprayOperator = sprayOperatorRepository.getOne(id);
		return foundSprayOperator != null ? ResponseEntity.ok(foundSprayOperator) : ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SprayOperator> edit(@PathVariable Long id, @Valid @RequestBody SprayOperator sprayOperator){
		SprayOperator updatedSprayOperator = sprayOperatorService.update(id, sprayOperator);
		return ResponseEntity.ok(updatedSprayOperator);
	}

}
