package com.musala.gateway.repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.gateway.model.PeripheralDevice;

public interface PeripheralDeviceRepository extends JpaRepository<PeripheralDevice, Integer> {

	List<PeripheralDevice> findByGatewayId(Long gatewayId);
	List<PeripheralDevice> deleteByGatewayId(Long gatewayId);

}
