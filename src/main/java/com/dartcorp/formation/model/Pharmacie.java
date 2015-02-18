package com.dartcorp.formation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pharmacie implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private String identif;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String numeroCommerce;
	
	@Column (nullable = false)
	private String dateCreation;

	@Column(nullable = false)
	private int employeSize;

	@Column(nullable = false)
	private String locality;
	
	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	@Column
	private Boolean isActif;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumeroCommerce() {
		return numeroCommerce;
	}

	public void setNumeroCommerce(String numeroCommerce) {
		this.numeroCommerce = numeroCommerce;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	public int getEmployeSize() {
		return employeSize;
	}

	public void setEmployeSize(int employeSize) {
		this.employeSize = employeSize;
	}

	
	public Boolean getIsActif() {
		return isActif;
	}

	public void setIsActif(Boolean isActif) {
		this.isActif = isActif;
	}
	
}