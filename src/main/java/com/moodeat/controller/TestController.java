package com.moodeat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("/hello/docker")
	public String getDockerHello() {
		return "Hello, World Docker";
	}
}
