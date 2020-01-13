package com.musala.gateway.controller;

import java.util.List;

import com.musala.gateway.response.GatewayResponse;
import com.musala.gateway.response.ValidationResult;
import com.musala.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.model.PeripheralDevice;
import com.musala.gateway.repositroy.GatewayRepository;
import com.musala.gateway.repositroy.PeripheralDeviceRepository;

@RestController
public class GatewayController {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	@Autowired
	private PeripheralDeviceRepository peripheralDeviceRepository;

	@Autowired
	private GatewayService gatewayService;
	
	@RequestMapping("/gateway")
	public String hello() {
		return "Hello world";
	}
	
	@RequestMapping("/getAll")
	public List<Gateway> getAll() {
		List<Gateway> gateways =  gatewayRepository.findAll();
		for (Gateway gateway : gateways) {
			List<PeripheralDevice> peripheralDevices = peripheralDeviceRepository.findByGatewayId(gateway.getId());
			gateway.setPeripheralDevices(peripheralDevices);
		}
		
		return gateways;
	}
	
	@PostMapping("/create")
	public GatewayResponse create(@RequestBody Gateway gateway) {

		ValidationResult validationResult = gatewayService.validateGateway(gateway);

		if (!validationResult.isValid()) {
			return new GatewayResponse(200, validationResult.getMessage());
		}

		Gateway persistedGateway = gatewayRepository.saveAndFlush(gateway);

		List<PeripheralDevice> peripheralDevices = gateway.getPeripheralDevices();
		for (PeripheralDevice peripheralDevice : peripheralDevices) {
			peripheralDevice.setGatewayId(persistedGateway.getId());
			peripheralDeviceRepository.saveAndFlush(peripheralDevice);
		}
		return new GatewayResponse(200, "Gateway is successfully added");
	}
}
