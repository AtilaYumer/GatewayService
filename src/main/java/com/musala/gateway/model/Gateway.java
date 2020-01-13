package com.musala.gateway.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gateway")
public class Gateway {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "serial_number", unique = true, nullable = false)
	private String serialNumber;

	@Column(name = "name")
	private String name;

	@Column(name = "ip", nullable = false)
	private String ip;

	@OneToMany(targetEntity = PeripheralDevice.class, mappedBy = "gatewayId", fetch = FetchType.EAGER)
	private List<PeripheralDevice> peripheralDevices;

	public Long getId() {
		return id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<PeripheralDevice> getPeripheralDevices() {
		return peripheralDevices;
	}

	public void setPeripheralDevices(List<PeripheralDevice> peripheralDevices) {
		this.peripheralDevices = peripheralDevices;
	}

}
