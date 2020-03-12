package com.bancaonline.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "consortium_token")
public class ConsortiumToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "consortium_token_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "consortium_id")
	private Consortium consortium;

	private String token;

	@Column(name = "token_location")
	private String tokenLocation;

	@Column(name = "short_url")
	@Size(min = 10, max = 100)
	private String shortUrl;

	@Column(name = "full_url")
	@Size(min = 10, max = 400)
	private String fullUrl;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@ManyToOne
	@JoinColumn(name = "token_type_id")
	@NotNull
	private TokenType tokenType;

	public ConsortiumToken() {
		super();
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

	public String getTokenLocation() {
		return tokenLocation;
	}

	public void setTokenLocation(String tokenLocation) {
		this.tokenLocation = tokenLocation;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

}
