package ec.edu.ups.rest;


import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClientFacade;
import ec.edu.ups.entities.Client;

@Path("/cliente")
public class ClienteResource {
	
	@EJB
	private ClientFacade clientFacade;
	
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createClient(Client client) {
		if (client != null) {
			clientFacade.create(client);
			return Response.status(Response.Status.CREATED).build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClients() {
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(clientFacade.findAll())).build();
	}
	

}
