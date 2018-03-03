package com.xavier.vectorlink.vectorlinksysapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xavier.vectorlink.vectorlinksysapi.model.Country;
import com.xavier.vectorlink.vectorlinksysapi.repository.CountryRepository;

@RestController
@RequestMapping("/countries")
public class CountryResource {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping
	public List<Country> listCountries(){
		return countryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Country> create(@Valid @RequestBody Country country, HttpServletResponse response) {
		Country savedCountry = countryRepository.save(country);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedCountry.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(savedCountry);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Country> findById(@PathVariable Long id) {
		Country foundCountry = countryRepository.getOne(id);
		
	    return foundCountry != null ? ResponseEntity.ok(foundCountry) : ResponseEntity.notFound().build();
	}

}
