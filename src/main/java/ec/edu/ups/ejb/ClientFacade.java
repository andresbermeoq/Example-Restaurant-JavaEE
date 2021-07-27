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

}
