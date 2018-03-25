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
import com.xavier.vectorlink.vectorlinksysapi.model.SprayDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayDetailsRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayDetailsFilter;
import com.xavier.vectorlink.vectorlinksysapi.service.SprayDetailsService;

@RestController
@RequestMapping("/spraydetails")
public class SprayDetailsResource {
	
	@Autowired
	private SprayDetailsRepository sprayDetailsRepository;
	
	@Autowired
	private SprayDetailsService sprayDetailsService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<SprayDetails> create(@Valid @RequestBody SprayDetails sprayDetails, HttpServletResponse response){
		SprayDetails savedSprayDetails = sprayDetailsService.save(sprayDetails);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedSprayDetails.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSprayDetails);
	}
	
	@GetMapping
	public Page<SprayDetails> search(SprayDetailsFilter sprayDetailsFilter, Pageable pageable ){
		return sprayDetailsRepository.filter(sprayDetailsFilter, pageable);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SprayDetails> edit(@PathVariable Long id, @Valid @RequestBody SprayDetails sprayDetails){
		SprayDetails updatedSprayDetails = sprayDetailsService.update(id, sprayDetails);
		return ResponseEntity.ok(updatedSprayDetails);
	}

}
