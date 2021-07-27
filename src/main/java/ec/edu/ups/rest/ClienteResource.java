package ec.edu.ups.rest;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import ec.edu.ups.ejb.ClientFacade;

@Path("/cliente")
public class ClienteResource {
	
	@EJB
	private ClientFacade clientFacade;
	
	public void createClient() {
		
	}
	

}
