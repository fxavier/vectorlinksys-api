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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.vectorlink.vectorlinksysapi.event.CreatedResourceEvent;
import com.xavier.vectorlink.vectorlinksysapi.exceptionhandler.VectorlinksysExceptionHandler.Error;
import com.xavier.vectorlink.vectorlinksysapi.model.Village;
import com.xavier.vectorlink.vectorlinksysapi.repository.VillageRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.VillageService;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.LocalityNotExistsOrInactiveException;

@RestController
@RequestMapping("/villages")
public class VillageResource {

	@Autowired
	private VillageRepository villageRepository;
	
	@Autowired
	private VillageService villageService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@PostMapping
	public ResponseEntity<Village> create(@Valid @RequestBody Village village, HttpServletResponse response){
		Village savedVillage = villageService.save(village);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedVillage.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(village);
	}
	
	@GetMapping
	public List<Village> search(){
		return villageRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Village> findById(@PathVariable Long id){
		Village village = villageRepository.getOne(id);
		return village != null ? ResponseEntity.ok(village) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Village> edit(@PathVariable Long id, @Valid @RequestBody Village village){
		Village savedVillage = villageService.update(id, village);
		return ResponseEntity.ok(savedVillage);
	}
	
	@ExceptionHandler({LocalityNotExistsOrInactiveException.class})
	public ResponseEntity<Object> handleLocalityNotExistsOrInactiveException(LocalityNotExistsOrInactiveException ex){
		String userMessage = messageSource.getMessage("localidade.inexistente-ou-inactivo", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}
}
