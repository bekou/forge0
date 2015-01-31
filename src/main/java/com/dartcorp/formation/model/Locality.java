package com.dartcorp.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locality {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column
	private String identif;
	@Column
	private String value;
	@OneToOne
	private Pharma pharma;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentif() {
		return identif;
	}
	public void setIdentif(String identif) {
		this.identif = identif;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public Pharma getPharma() {
		return pharma;
	}
	public void setPharma(Pharma pharma) {
		this.pharma = pharma;
	}


	
}