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
import com.xavier.vectorlink.vectorlinksysapi.model.SprayTotals;
import com.xavier.vectorlink.vectorlinksysapi.repository.SprayTotalsRepository;
import com.xavier.vectorlink.vectorlinksysapi.repository.filter.SprayTotalsFilter;
import com.xavier.vectorlink.vectorlinksysapi.service.SprayTotalsService;

@RestController
@RequestMapping("/sprayTotals")
public class SprayTotalsResource {
	
	@Autowired
	private SprayTotalsService sprayTotalsService;
	
	@Autowired
	private SprayTotalsRepository sprayTotalsRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<SprayTotals> create(@Valid @RequestBody SprayTotals sprayTotals, HttpServletResponse response){
		SprayTotals savedSprayTotals = sprayTotalsService.save(sprayTotals);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedSprayTotals.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSprayTotals);
	}
	
	@GetMapping
	public List<SprayTotals> search(SprayTotalsFilter sprayTotalsFilter){
		return sprayTotalsRepository.findAll();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SprayTotals> edit(@PathVariable Long id, @Valid @RequestBody SprayTotals sprayTotals){
		SprayTotals updatedSprayTotals = sprayTotalsService.update(id, sprayTotals);
		return ResponseEntity.ok(updatedSprayTotals);
	}

}
