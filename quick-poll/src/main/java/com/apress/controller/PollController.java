package com.apress.controller;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RestController;
import com.apress.repository.PollRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.apress.domain.Poll;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
/*
 * Provides end-points to access and modify Poll/s resources.
 */

@Controller("Poll")
@RestController
public class PollController 
{

	@Inject
	private PollRepository pollRepository;
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllPolls()
	{
		Iterable<Poll> allPolls = pollRepository.findAll();
		return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK );
	}
	
	// create a new Poll
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@RequestBody Poll poll)
	{
		poll = pollRepository.save(poll);
		
		// sets location header for the newly created resource.
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
}
