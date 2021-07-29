package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {
	
	@PersistenceContext(unitName = "App-Reservations")
	private EntityManager em;

	public ClientFacade() {
		super(Client.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Client getClientbyIdCard(String cedula) {
		String query = "SELECT c FROM Client c WHERE c.idCard = :cedula";
		Client client = null;
		
		try {
			client = (Client) em.createQuery(query)
								.setParameter("cedula", cedula)
								.getSingleResult();
		} catch (Exception e) {
			System.out.println("--> Error: ClientFacade: getClientbyIdCard: " + e.getMessage() );
		}
		return client;
	}

}
