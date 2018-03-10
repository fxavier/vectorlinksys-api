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
import com.xavier.vectorlink.vectorlinksysapi.model.District;
import com.xavier.vectorlink.vectorlinksysapi.repository.DistrictRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.DistrictService;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.ProvinceNotExistsOrInactiveException;

@RestController
@RequestMapping("/districts")
public class DistrictResource {
	
	@Autowired
	private DistrictService districtService;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<District> search(){
		return districtRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<District> findById(@PathVariable Long id){
		District district = districtRepository.getOne(id);
		return district != null ? ResponseEntity.ok(district) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<District> create(@Valid @RequestBody District district, HttpServletResponse response){
		District savedDistrict = districtService.save(district);
		
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedDistrict.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDistrict);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long id) {
		districtRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<District> edit(@PathVariable Long id, @Valid @RequestBody District district){
		District savedDistrict = districtService.update(id, district);
		return ResponseEntity.ok(savedDistrict);
	}
	
	
	@ExceptionHandler({ProvinceNotExistsOrInactiveException.class})
	public ResponseEntity<Object> handleProvinceNotExistsOrInactiveException(ProvinceNotExistsOrInactiveException ex){
	
	String userMessage = messageSource.getMessage("provincia.inexistente-ou-inactivo", null, LocaleContextHolder.getLocale());
	String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
	List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
	return ResponseEntity.badRequest().body(errors);
	}
}
