package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the "Reservation" database table.
 * 
 */
@Entity
@Table(name="\"Reservation\"")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="capacity_number")
	private Integer capacityNumber;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Time hour;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="user_id_card", referencedColumnName="id_card")
	private Client client;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne
	private Restaurant restaurant;

	public Reservation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacityNumber() {
		return this.capacityNumber;
	}

	public void setCapacityNumber(Integer capacityNumber) {
		this.capacityNumber = capacityNumber;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHour() {
		return this.hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}