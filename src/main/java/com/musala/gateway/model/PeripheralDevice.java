package com.musala.gateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.musala.gateway.model.enumeration.DeviceStatus;

@Entity
@Table(name = "peripheral_device")
public class PeripheralDevice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uid;

	private String vendor;

	private long creationDate;

	private DeviceStatus status;

	private Long gateway_id;

	@Column(name = "uid", unique = true)
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "vendor")
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(name = "creation_date")
	public long getTimestamp() {
		return creationDate;
	}

	public void setTimestamp(long timestamp) {
		this.creationDate = timestamp;
	}

	@Column(name = "status")
	public DeviceStatus getStatus() {
		return status;
	}

	public void setStatus(DeviceStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn
	public Long getGateway_id() {
		return gateway_id;
	}

	public void setGateway_id(Long gateway_id) {
		this.gateway_id = gateway_id;
	}
}