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
import com.xavier.vectorlink.vectorlinksysapi.model.TeamLeader;
import com.xavier.vectorlink.vectorlinksysapi.repository.TeamLeaderRepository;
import com.xavier.vectorlink.vectorlinksysapi.service.TeamLeaderService;

@RestController
@RequestMapping("/teamLeaders")
public class TeamLeaderResource {
	
	@Autowired
	private TeamLeaderRepository teamLeaderRepository;
	
	@Autowired
	private TeamLeaderService teamLeaderService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<TeamLeader> create(@Valid @RequestBody TeamLeader teamLeader, HttpServletResponse response){
		TeamLeader savedTeamLeader = teamLeaderService.save(teamLeader);
		publisher.publishEvent(new CreatedResourceEvent(this, response, savedTeamLeader.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeamLeader);
	}
	
	@GetMapping
	public List<TeamLeader> search(){
		return teamLeaderRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TeamLeader> findById(@PathVariable Long id){
		TeamLeader teamLeader = teamLeaderRepository.getOne(id);
		return teamLeader != null ? ResponseEntity.ok(teamLeader) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TeamLeader> edit(@PathVariable Long id, @Valid @RequestBody TeamLeader teamLeader){
		TeamLeader updatedTeamLeader = teamLeaderService.update(id, teamLeader);
		return ResponseEntity.ok(updatedTeamLeader);
	}

}
