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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xavier.vectorlink.vectorlinksysapi.event.CreatedResourceEvent;
import com.xavier.vectorlink.vectorlinksysapi.exceptionhandler.VectorlinksysExceptionHandler.Error;
import com.xavier.vectorlink.vectorlinksysapi.model.Province;
import com.xavier.vectorlink.vectorlinksysapi.repository.ProvinceRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.ProvinceService;
import com.xavier.vectorlink.vectorlinksysapi.service.exception.CountryNotExistsOrInactiveException;

@RestController
@RequestMapping("/provinces")
public class ProvinceResource {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Province> listProvinces(){
		return provinceRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Province> findById(@PathVariable Long id){
		Province province = provinceRepository.getOne(id);
		return province != null ? ResponseEntity.ok(province) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Province> create(@Valid @RequestBody Province province, HttpServletResponse response){
		 Province savedProvince = provinceService.save(province);
		 
		 publisher.publishEvent(new CreatedResourceEvent(this, response, savedProvince.getId()));
		 return ResponseEntity.status(HttpStatus.CREATED).body(savedProvince);
	}
	
	@ExceptionHandler({CountryNotExistsOrInactiveException.class})
	public ResponseEntity<Object> handleCountryNotExistsOrInactiveException(CountryNotExistsOrInactiveException ex){
		String userMessage = messageSource.getMessage("pais.inexistente-ou-inactivo", null, LocaleContextHolder.getLocale());
		String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
		return ResponseEntity.badRequest().body(errors);
	}

}
