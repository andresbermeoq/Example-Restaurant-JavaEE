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

import ec.edu.ups.ejb.RestaurantFacade;
import ec.edu.ups.entities.Restaurant;

@Path("/restaurante")
public class RestaurantResource {
	
	@EJB
	private RestaurantFacade restaurantFacade;
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRestaurant(Restaurant restaurant) {
		if (restaurant != null) {
			try {
				restaurantFacade.create(restaurant);
				return Response.status(Response.Status.CREATED).entity(restaurant)
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
	public Response getRestaurants() {
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(restaurantFacade.findAll()))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}
	
	

}
