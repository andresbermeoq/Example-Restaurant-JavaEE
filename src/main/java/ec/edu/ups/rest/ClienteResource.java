package ec.edu.ups.rest;


import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
			try {
				clientFacade.create(client);
				return Response.status(Response.Status.CREATED).entity(client)
							.header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
							.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(500)
							.header("Access-Control-Allow-Origin", "*")
							.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
							.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity(400)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		}
	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClients() {
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(clientFacade.findAll()))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	@GET
	@Path("/buscarCedula")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientbyIdCar(@QueryParam("idCard") String idCard) {
		Client client = new Client();
		try {
			
			client = clientFacade.getClientbyIdCard(idCard);
			if(client == null) {
				return Response.status(Response.Status.NOT_FOUND).entity(404)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
						.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
			} else {
				return Response.ok(client)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
						.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
			}
			
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(500)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
		}
		
	}
	

}
