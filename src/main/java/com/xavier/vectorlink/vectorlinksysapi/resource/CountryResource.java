package com.xavier.vectorlink.vectorlinksysapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.vectorlink.vectorlinksysapi.event.CreatedResourceEvent;
import com.xavier.vectorlink.vectorlinksysapi.model.Country;
import com.xavier.vectorlink.vectorlinksysapi.repository.CountryRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryResource {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Country> listCountries(){
		return countryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Country> create(@Valid @RequestBody Country country, HttpServletResponse response) {
		Country savedCountry = countryRepository.save(country);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedCountry.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCountry);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Country> findById(@PathVariable Long id) {
		Country foundCountry = countryRepository.getOne(id);
		
	    return foundCountry != null ? ResponseEntity.ok(foundCountry) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		countryRepository.deleteById(id);;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Country> edit(@PathVariable Long id, @Valid @RequestBody Country country){
		Country savedCountry = countryService.update(id, country);
		return ResponseEntity.ok(savedCountry);
	}

}
