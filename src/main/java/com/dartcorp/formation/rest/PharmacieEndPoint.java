package com.dartcorp.formation.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.dartcorp.formation.ejb.PharmacieEJB;
import com.dartcorp.formation.model.Pharmacie;

@Path("/pharmacies")
public class PharmacieEndPoint {

	@Inject
	private PharmacieEJB pharmacieEJB;

	@POST
	@Consumes("application/json")
	public Response create(Pharmacie entity) {
		entity = pharmacieEJB.create(entity);
		return Response.created(
				UriBuilder.fromResource(PharmacieEndPoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") Long id, Pharmacie p) {
		p = pharmacieEJB.update(p);
		if( p == null ) {
			return Response.status(Status.NOT_FOUND).build();
		}
		//return Response.ok(p).build();
		return Response.status(Status.OK).entity(p).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		pharmacieEJB.delete(id);
		return Response.noContent().build();
	}//implemente le filtre recherche lie a la recherche byId

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		Pharmacie p = pharmacieEJB.findById(id);
		return Response.ok(p).build();
	}

	@GET
	@Produces("application/json")
	public List<Pharmacie> listAll() {
		List<Pharmacie> results = pharmacieEJB.listAll();
		return results;
	}

}