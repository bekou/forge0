package com.dartcorp.formation.model;

public class Form {
	
	private String name;
	private String numeroCommerce;
	private int employeSize;
	private String locality;
	private boolean actif;

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

	public int getEmployeSize() {
		return employeSize;
	}

	public void setEmployeSize(int employeSize) {
		this.employeSize = employeSize;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
}
