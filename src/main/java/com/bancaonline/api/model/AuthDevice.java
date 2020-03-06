package com.bancaonline.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "auth_device")
public class AuthDevice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "auth_device_id")
	private Long id;

	private String ip;

	private String token;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	public AuthDevice(String ip, String token, Status status) {
		
		this.ip = ip;
		this.token = token;
		this.status = status;
	}

	public AuthDevice(Long id, String ip, String token, Status status) {
		
		this.id = id;
		this.ip = ip;
		this.token = token;
		this.status = status;
	}
	
	

	public AuthDevice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
