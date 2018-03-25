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
import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationDetails;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizationDetailsRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.MobilizationDetailsFilter;
import com.xavier.vectorlink.vectorlinksysapi.service.MobilizationDetailsService;

@RestController
@RequestMapping("/mobdetails")
public class MobilizationDetailsResource {
	
	@Autowired
	private MobilizationDetailsService service;
	
	@Autowired
	private MobilizationDetailsRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<MobilizationDetails> create(@Valid @RequestBody MobilizationDetails mobilizationDetails
			, HttpServletResponse response){
		MobilizationDetails savedMobDetails = service.save(mobilizationDetails);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedMobDetails.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMobDetails);
	}
	
	@GetMapping
	public Page<MobilizationDetails> search(MobilizationDetailsFilter mobilizationDetailsFilter, Pageable pageable){
		return repository.filter(mobilizationDetailsFilter, pageable);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MobilizationDetails> edit(@PathVariable Long id, @Valid @RequestBody MobilizationDetails mobilizationDetails){
		MobilizationDetails updatedMobDetails = service.update(id, mobilizationDetails);
		return ResponseEntity.ok(updatedMobDetails);
	}

}
