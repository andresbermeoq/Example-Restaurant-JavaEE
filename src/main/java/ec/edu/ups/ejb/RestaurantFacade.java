package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Restaurant;

@Stateless
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
	
	public Restaurant getNameRestaurant (Integer idRestaurant) {
		String query = "SELECT re FROM Restaurant re WHERE re.id= :id";
		Restaurant restaurant = null;
		
		try {
			restaurant = (Restaurant) em.createQuery(query)
										.setParameter("id", idRestaurant)
										.getSingleResult();

		} catch (Exception e) {
			System.out.println("--> Error: RestaurantFacade: getNameRestaurant: " + e.getMessage() );
		}
		
		return restaurant;
		
	}

}
