package com.musala.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.repositroy.GatewayRepository;

@RestController
public class GatewayController {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	@RequestMapping("/gateway")
	public String hello() {
		return "Hello world";
	}
	
	@RequestMapping("/getAll")
	public List<Gateway> getAll() {
		return gatewayRepository.findAll();
	}
	
	@PostMapping("/create")
	public Gateway create(@RequestBody Gateway gateway) {
		
		return gatewayRepository.saveAndFlush(gateway);
	}
}
