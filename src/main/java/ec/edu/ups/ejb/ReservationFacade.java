package ec.edu.ups.ejb;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	public int getReservationByDate(String name, LocalDate date, LocalTime hour) {
		String query = "SELECT res FROM Reservation res, Restaurant ret "
					+ "	WHERE ret.name = :name"
					+ "	AND res.date= :date AND res.hour= :hour";
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		int aforo = 0; 
		reservations = em.createQuery(query,Reservation.class)
										.setParameter("date", date)
										.setParameter("hour", hour)
										.setParameter("name", name) 
										.getResultList();
		
			for(Reservation reservation: reservations) {
					aforo = aforo + reservation.getCapacityNumber();
			}
			return aforo;
	}
	
	public List<Reservation> getClients(String idCard) {
		List<Reservation> clientsList = new ArrayList<Reservation>();
		
		String query = "Select res FROM Reservation res, Client cli WHERE cli.idCard = :idCard";
		
		clientsList = em.createQuery(query, Reservation.class).setParameter("idCard", idCard).getResultList();
		
		return clientsList;
	}
	
	public List<Reservation> getRestaurants(String name, LocalDate date) {
		List<Reservation> restaurantsList = new ArrayList<Reservation>();
		
		String query = "Select res FROM Reservation res, Restaurant rest WHERE rest.name = :name AND res.date= :date";
		
		restaurantsList = em.createQuery(query, Reservation.class)
							.setParameter("name", name)
							.setParameter("date", date)
							.getResultList();
		
		return restaurantsList;
	}
	
	

}
