package com.twitter.controller;

import java.util.Collection;

import com.twitter.model.UserMessage;
import com.twitter.model.dto.FollowRequest;
import com.twitter.model.dto.UserMessageRequest;
import com.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TwitterController {
	@Autowired
	private TwitterService twitterService;

	@PostMapping("/")
	public ResponseEntity<UserMessage> addMessage(@RequestBody @Valid UserMessageRequest userMessageRequest) {
		UserMessage userMessage = twitterService.addMessage(userMessageRequest);
		return new ResponseEntity<>(userMessage, HttpStatus.OK);
	}

	@GetMapping("/wall/{userEmail}")
	public ResponseEntity<Collection<UserMessage>> getWall(@PathVariable("userEmail") String userEmail) {
		Collection<UserMessage> wall = twitterService.getWall(userEmail);
		return new ResponseEntity<>(wall, HttpStatus.OK);
	}

	@PostMapping("/follow")
	public ResponseEntity follow(@RequestBody FollowRequest followRequest) {
		twitterService.follow(followRequest.getUserEmail(), followRequest.getFriendEmail());
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/timeline/{userEmail}")
	public ResponseEntity<Collection<UserMessage>> getTimeline(@PathVariable("userEmail") String userEmail) {
		Collection<UserMessage> timeline = twitterService.getTimeline(userEmail);
		return new ResponseEntity<>(timeline, HttpStatus.OK);
	}

}