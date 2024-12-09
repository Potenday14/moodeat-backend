package com.moodeat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
	@GetMapping("/hello")
	public String home() {
		return "Hello, World NCP";
	}

	@GetMapping("/hello/docker")
	public String helloDocker() {
		return "Hello, World Docker";
	}
}
