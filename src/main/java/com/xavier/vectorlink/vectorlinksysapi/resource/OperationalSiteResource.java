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
import com.xavier.vectorlink.vectorlinksysapi.model.OperationalSite;
import com.xavier.vectorlink.vectorlinksysapi.repository.OperationalSiteRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.OperationalSiteService;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.DistrictNotExistsOrInactiveException;

@RestController
@RequestMapping("/operationalSites")
public class OperationalSiteResource {
	
	@Autowired
	private OperationalSiteService operationalSiteService;
	
	@Autowired
	private OperationalSiteRepository operationalSiteRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<OperationalSite> create(@Valid @RequestBody OperationalSite operationalSite, HttpServletResponse response ){
		OperationalSite savedOperationalSite = operationalSiteService.save(operationalSite);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedOperationalSite.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOperationalSite);
	}
	
	@GetMapping
	public List<OperationalSite> search(){
		return operationalSiteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OperationalSite> findById(@PathVariable Long id){
		OperationalSite operationalSite = operationalSiteRepository.getOne(id);
		return operationalSite != null ? ResponseEntity.ok(operationalSite) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OperationalSite> edit(@PathVariable Long id, @Valid @RequestBody OperationalSite operationalSite){
		OperationalSite savedOperationalSite = operationalSiteService.update(id, operationalSite);
		return ResponseEntity.ok(savedOperationalSite);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		operationalSiteRepository.deleteById(id);
	}
	
	@ExceptionHandler({DistrictNotExistsOrInactiveException.class})
	public ResponseEntity<Object> handleDistrictNotExistsOrInactiveException(DistrictNotExistsOrInactiveException ex){
		String userMessage = messageSource.getMessage("distrito.inexistente-ou-inactivo", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}
	

}
