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
import com.xavier.vectorlink.vectorlinksysapi.model.MobilizationTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.MobilizationTotalsRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.MobilizationTotalsFilter;
import com.xavier.vectorlink.vectorlinksysapi.service.MobilizationTotalsService;

@RestController
@RequestMapping("/mobTotals")
public class MobilizationTotalsResource {

	@Autowired
	private MobilizationTotalsRepository mobTotalsRepository;
	
	@Autowired
	private MobilizationTotalsService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<MobilizationTotals> create(@Valid @RequestBody MobilizationTotals mobilizationTotals
			  , HttpServletResponse response){
		MobilizationTotals savedMobTotals = service.save(mobilizationTotals);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedMobTotals.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedMobTotals);
		
	}
	
	@GetMapping
	public Page<MobilizationTotals> search(MobilizationTotalsFilter mobilizationTotalsFilter, Pageable pageable){
		return mobTotalsRepository.filter(mobilizationTotalsFilter, pageable);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MobilizationTotals> edit(@PathVariable Long id, @Valid @RequestBody MobilizationTotals mobilizationTotals){
		MobilizationTotals updatedMobTotals = service.update(id, mobilizationTotals);
	  return ResponseEntity.ok(updatedMobTotals);
	}
}
