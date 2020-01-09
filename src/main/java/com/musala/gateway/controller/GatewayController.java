package com.musala.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gateway.repositroy.GatewayRepository;

@RestController
public class GatewayController {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	@RequestMapping("/gateway")
	public String hello() {
		return "Hello world";
	}
	
	@PostMapping("/create")
	public String create() {
		
		return "";
	}
}
