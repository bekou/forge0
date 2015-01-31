package com.dartcorp.formation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Pharma {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String identif;
	@Column
	private String name;
	@Column
	private String numeroCommerce;
	@Column
	private Date dateCreation;
	@Column
	private int employeSize;
	@Column
	private boolean isActif;

	
	public String getIdentif() {
		return identif;
	}

	public void setIdentif(String identif) {
		this.identif = identif;
	}

	public String getName() {
		return name;
	}

	public void setName(Form form) {
		this.name = form.getName();
	}

	public String getNumeroCommerce() {
		return numeroCommerce;
	}

	public void setNumeroCommerce(Form form) {
		this.numeroCommerce = form.getNumeroCommerce();
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getEmployeSize() {
		return employeSize;
	}

	public void setEmployeSize(Form form) {
		this.employeSize = form.getEmployeSize();
	}

	public boolean isActif() {
		return isActif;
	}

	public void setActif(Form form) {
		this.isActif = form.isActif();
	}
}