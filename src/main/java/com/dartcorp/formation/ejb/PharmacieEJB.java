package com.dartcorp.formation.ejb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.dartcorp.formation.model.Pharmacie;

@Stateless
public class PharmacieEJB {

	@PersistenceContext(unitName = "pharma-persistence-unit")
	private EntityManager em;

	public Pharmacie create(Pharmacie p) {
		p.setDateCreation(dateCreation);
		p.setIdentif(p.getName().substring(0, 3)+"000");
		if(p.getIsActif()==null){
			p.setIsActif(true);
		}
		em.persist(p);
	
		return p;
	}

	public Pharmacie update(Pharmacie p) {
		Pharmacie p1 = findById(p.getId());
		if (p1 == null) {
			return p1;
		}
		
		return em.merge(p);

	}

	public void delete(Long id) {
		Pharmacie p = em.find(Pharmacie.class, id);
		em.remove(p);
	}

	public Pharmacie findById(Long id) {
		TypedQuery<Pharmacie> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Pharmacie p WHERE p.id = :entityId ORDER BY p.id",
						Pharmacie.class);
		findByIdQuery.setParameter("entityId", id);
		Pharmacie p;

		p = findByIdQuery.getSingleResult();
		return p;
	}

	public List<Pharmacie> listAll() {
		TypedQuery<Pharmacie> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM Pharmacie p ORDER BY p.id",
				Pharmacie.class);

		final List<Pharmacie> results = findAllQuery.getResultList();
		return results;
	}

	SimpleDateFormat t = new SimpleDateFormat("dd-MM-yyyy");
	String dateCreation = t.format(new Date());
}