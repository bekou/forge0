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
	@Column
	private String identif;
	@Column
	private String name;
	@Column
	private String numeroCommerce;
	@Column
	private Date dateCreation;
	@Column
	private String locality;
	@Column
	private int employeSize;
	@Column
	private boolean isActif;

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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public int getEmployeSize() {
		return employeSize;
	}

	public void setEmployeSize(int employeSize) {
		this.employeSize = employeSize;
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(boolean isActif) {
		this.isActif = isActif;
	}
	
}