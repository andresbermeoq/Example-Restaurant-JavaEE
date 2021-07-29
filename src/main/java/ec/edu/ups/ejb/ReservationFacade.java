package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Reservation;

@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> {
	
	@PersistenceContext(unitName = "App-Reservations")
	private EntityManager em;

	public ReservationFacade() {
		super(Reservation.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	

}
