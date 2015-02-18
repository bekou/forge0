package com.dartcorp.formation.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dartcorp.formation.model.Locality;
import com.dartcorp.formation.model.Pharmacie;

@Stateless
public class LocalityEJB {

	
	@PersistenceContext(unitName = "pharma-persistence-unit")
	private EntityManager em;

	Pharmacie p;
	public Locality create(Locality l) {
		em.persist(l);
		return l;
	}
	
}