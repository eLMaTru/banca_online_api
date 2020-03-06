package com.bancaonline.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "consortium_token")
public class ConsortiumToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "consortium_token_id")
	private Long id;
	
	
	private Consortium consortium;
	
	private String token;
	
	private Status status;

	public ConsortiumToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsortiumToken(Consortium consortium, String token, Status status) {
		super();
		this.consortium = consortium;
		this.token = token;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Consortium getConsortium() {
		return consortium;
	}

	public void setConsortium(Consortium consortium) {
		this.consortium = consortium;
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
