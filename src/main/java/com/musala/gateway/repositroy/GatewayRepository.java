package com.musala.gateway.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.gateway.model.Gateway;

public interface GatewayRepository extends JpaRepository<Gateway, Integer> {

	Gateway findBySerialNumber(String serialNumber);
	Gateway deleteBySerialNumber(String serialNumber);
}
