package com.xavier.vectorlink.vectorlinksysapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.xavier.vectorlink.vectorlinksysapi.model.Actor;
import com.xavier.vectorlink.vectorlinksysapi.repository.ActorRepository;

@Service
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	public Actor save(Actor actor) {
		return actorRepository.save(actor);
	}
	
	public Actor update(Long id, Actor actor) {
		Actor savedActor = actorRepository.getOne(id);
		if(savedActor == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(actor, savedActor, "id");
		return actorRepository.save(savedActor);
	}

}
