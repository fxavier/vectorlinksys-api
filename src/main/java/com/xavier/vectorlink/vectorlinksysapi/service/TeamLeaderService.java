package com.xavier.vectorlink.vectorlinksysapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.TeamLeader;
import com.xavier.vectorlink.vectorlinksysapi.repository.TeamLeaderRepository;

@Service
public class TeamLeaderService {
	
	@Autowired
	private TeamLeaderRepository teamLeaderRepository;
	
	public TeamLeader save(TeamLeader teamLeader) {
		return teamLeaderRepository.save(teamLeader);
	}
	
	public TeamLeader update(Long id, TeamLeader teamLeader) {
		TeamLeader foundTeamLeader = teamLeaderRepository.getOne(id);
		if(foundTeamLeader == null) {
			throw new EntityNotFoundException();
		}
		
		BeanUtils.copyProperties(teamLeader, foundTeamLeader, "id");
		return teamLeaderRepository.save(foundTeamLeader);
	}

}
