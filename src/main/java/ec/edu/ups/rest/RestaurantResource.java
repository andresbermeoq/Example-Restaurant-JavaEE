package ec.edu.ups.rest;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import ec.edu.ups.ejb.RestaurantFacade;

@Path("/restaurante")
public class RestaurantResource {
	
	@EJB
	private RestaurantFacade restaurantFacade;
	
	

}
