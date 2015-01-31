
package com.dartcorp.formation.ejb;

import com.dartcorp.formation.model.Command;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CommandEJB {
    
   @PersistenceContext(unitName = "pharma-persistence-unit")
   private EntityManager em;
   
    public Command create(Command entity){
        em.persist(entity);
        return entity;
    }
    
}