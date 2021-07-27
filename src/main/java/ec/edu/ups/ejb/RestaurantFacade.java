package ec.edu.ups.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Restaurant;

public class RestaurantFacade extends AbstractFacade<Restaurant> {
	
	@PersistenceContext(unitName = "App-Reservations")
	private EntityManager em;
	
	public RestaurantFacade() {
		super(Restaurant.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
