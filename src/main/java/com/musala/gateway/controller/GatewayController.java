package com.musala.gateway.controller;

import java.util.List;

import com.musala.gateway.response.GatewayResponse;
import com.musala.gateway.response.ValidationResult;
import com.musala.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.musala.gateway.model.Gateway;
import com.musala.gateway.model.PeripheralDevice;
import com.musala.gateway.repositroy.GatewayRepository;
import com.musala.gateway.repositroy.PeripheralDeviceRepository;

import javax.transaction.Transactional;

@RestController
public class GatewayController {

	@Autowired
	private GatewayRepository gatewayRepository;
	
	@Autowired
	private PeripheralDeviceRepository peripheralDeviceRepository;

	@Autowired
	private GatewayService gatewayService;

	@RequestMapping("/getAll")
	public List<Gateway> getAll() {
		List<Gateway> gateways =  gatewayRepository.findAll();
		for (Gateway gateway : gateways) {
			List<PeripheralDevice> peripheralDevices = peripheralDeviceRepository.findByGatewayId(gateway.getId());
			gateway.setPeripheralDevices(peripheralDevices);
		}

		return gateways;
	}

	@RequestMapping("/get/{serialNumber}")
	public Gateway getGateway(@PathVariable String serialNumber) {
		return gatewayRepository.findBySerialNumber(serialNumber);
	}

	@Transactional
	@DeleteMapping("/delete/{serialNumber}")
	public GatewayResponse deleteGateway(@PathVariable String serialNumber){

		//Find the gateway with the given serial number
		Gateway gateway = gatewayRepository.findBySerialNumber(serialNumber);

		if (gateway == null)
			return new GatewayResponse(500, "Gateway with serial number " + serialNumber + " does not exists");

		//Delete the peripheral devices of the gateway to delete all references to it
		peripheralDeviceRepository.deleteByGatewayId(gateway.getId());

		//Finally delete the gateway
		gatewayRepository.delete(gateway);

		return new GatewayResponse(200, "Gateway with serial number " + serialNumber + " is successfully deleted");
	}

	@Transactional
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
