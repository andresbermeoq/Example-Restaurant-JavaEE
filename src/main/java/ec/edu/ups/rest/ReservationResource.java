package ec.edu.ups.rest;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClientFacade;
import ec.edu.ups.ejb.ReservationFacade;
import ec.edu.ups.ejb.RestaurantFacade;
import ec.edu.ups.entities.Client;
import ec.edu.ups.entities.Reservation;
import ec.edu.ups.entities.Restaurant;


@Path("/reservar")
public class ReservationResource {
	
	@EJB
	private ReservationFacade reservationFacade;
	
	@EJB
	private RestaurantFacade restaurantFacade;
	
	@EJB
	private ClientFacade clientFacade;
	
	@POST
	@Path("/crear")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReservation(@FormParam("fecha") String date, @FormParam("hora") String hora, 
			@FormParam("cedula") String cedula, @FormParam("restaurante") Integer restaurante, @FormParam("capacidad") Integer capacidad) {
		
		Restaurant restaurant = restaurantFacade.getNameRestaurant(restaurante);
		Client client = clientFacade.getClientbyIdCard(cedula);
		Integer aforoRestaurante = reservationFacade.getReservationByDate(restaurant.getName(), LocalDate.parse(date), LocalTime.parse(hora));
		Integer disponible = restaurant.getCapacityNumber() - aforoRestaurante;
		
		if (disponible >= capacidad) {
			System.out.println("Puede registrarse");
			Reservation reservar = new Reservation();
			reservar.setCapacityNumber(capacidad);
			reservar.setClient(client);
			reservar.setDate(LocalDate.parse(date));
			reservar.setHour(LocalTime.parse(hora));
			reservar.setRestaurant(restaurant);
			reservationFacade.create(reservar);
			return Response.status(Response.Status.CREATED).entity(reservar).build();
			
		} else {
			System.out.println("No puede Registrarse");
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(406).build();
		}

	}
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations() {
		Jsonb jsonb = JsonbBuilder.create();
		return Response.ok(jsonb.toJson(reservationFacade.findAll())).build();
	}

}
