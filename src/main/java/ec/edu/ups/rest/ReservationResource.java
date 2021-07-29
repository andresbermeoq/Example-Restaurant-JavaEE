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

import ec.edu.ups.ejb.ReservationFacade;
import ec.edu.ups.entities.Reservation;

@Path("/reservar")
public class ReservationResource {
	
	@EJB
	private ReservationFacade reservationFacade;
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReservation(Reservation reservation) {
		if (reservation != null) {
			try {
				reservationFacade.create(reservation);
				return Response.status(Response.Status.CREATED).entity(reservation)
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
	public Response getReservations() {
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(reservationFacade.findAll()))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin,content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}

}
