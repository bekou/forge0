package com.dartcorp.formation.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.dartcorp.formation.model.Command;

/**
 * 
 */
@Stateless
@Path("/commands")
public class CommandEndpoint
{
   @PersistenceContext(unitName = "pharma-persistence-unit")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Command entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(CommandEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Command entity = em.find(Command.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Command> findByIdQuery = em.createQuery("SELECT DISTINCT c FROM Command c WHERE c.id = :entityId ORDER BY c.id", Command.class);
      findByIdQuery.setParameter("entityId", id);
      Command entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Command> listAll(@QueryParam("start") Integer startPosition, @QueryParam("max") Integer maxResult)
   {
      TypedQuery<Command> findAllQuery = em.createQuery("SELECT DISTINCT c FROM Command c ORDER BY c.id", Command.class);
      if (startPosition != null)
      {
         findAllQuery.setFirstResult(startPosition);
      }
      if (maxResult != null)
      {
         findAllQuery.setMaxResults(maxResult);
      }
      final List<Command> results = findAllQuery.getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(@PathParam("id") Long id, Command entity)
   {
      if (entity == null)
      {
         return Response.status(Status.BAD_REQUEST).build();
      }
      if (!id.equals(entity.getId()))
      {
         return Response.status(Status.CONFLICT).entity(entity).build();
      }
      if (em.find(Command.class, id) == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      try
      {
         entity = em.merge(entity);
      }
      catch (OptimisticLockException e)
      {
         return Response.status(Response.Status.CONFLICT).entity(e.getEntity()).build();
      }

      return Response.noContent().build();
   }

   @GET
   @Path("/findByClientId/{clientId}")
   @Produces("application/json")
   public Response findByClientId(@PathParam("clientId") Long clientId)
   {
      TypedQuery<Command> findByClientIdQuery = em.createQuery("SELECT DISTINCT c FROM Command c WHERE c.client.id = :clientId ORDER BY c.id", Command.class);
      findByClientIdQuery.setParameter("clientId", clientId);
      List<Command> commands = new ArrayList<Command>();
      try
      {
    	  commands = findByClientIdQuery.getResultList();
      }
      catch (NoResultException nre)
      {
    	  commands = null;
      }
      if (commands == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(commands).build();
   }
}
