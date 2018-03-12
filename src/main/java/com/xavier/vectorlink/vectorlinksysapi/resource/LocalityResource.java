package com.xavier.vectorlink.vectorlinksysapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.vectorlink.vectorlinksysapi.event.CreatedResourceEvent;
import com.xavier.vectorlink.vectorlinksysapi.exceptionhandler.VectorlinksysExceptionHandler.Error;
import com.xavier.vectorlink.vectorlinksysapi.model.Locality;
import com.xavier.vectorlink.vectorlinksysapi.repository.LocalityRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.LocalityService;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.OperationalSiteNotExistsOrInactiveException;

@RestController
@RequestMapping("/localities")
public class LocalityResource {
	
	@Autowired
	private LocalityService localityService;
	
	@Autowired
	private LocalityRepository localityRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<Locality> create(@Valid @RequestBody Locality locality, HttpServletResponse response){
		Locality savedLocality = localityService.save(locality);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedLocality.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLocality);
	}
	
	@GetMapping
	public List<Locality> search(){
		return localityRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Locality> findById(@PathVariable Long id){
		Locality locality = localityRepository.getOne(id);
		return locality != null ? ResponseEntity.ok(locality) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Locality> edit(@PathVariable Long id, @Valid @RequestBody Locality locality){
		Locality savedLocality = localityService.update(id, locality);
		return ResponseEntity.ok(savedLocality);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id){
		localityRepository.deleteById(id);
		
	}
	
	@ExceptionHandler({OperationalSiteNotExistsOrInactiveException.class})
	public ResponseEntity<Object> handleOperationalSiteNotExistsOrInactiveException(OperationalSiteNotExistsOrInactiveException ex){
		String userMessage = messageSource.getMessage("base.inexistente-ou-inactivo", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}

}
