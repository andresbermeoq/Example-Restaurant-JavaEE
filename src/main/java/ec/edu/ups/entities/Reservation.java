package ec.edu.ups.entities;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;



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

	@JsonbDateFormat(value = "MM/dd/yyyy")
	private LocalDate date;

	@JsonbDateFormat(value = "HH:mm")
	private LocalTime hour;

	//bi-directional many-to-one association to Client
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id_card", referencedColumnName="id_card")
	private Client client;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Restaurant restaurant;

	public Reservation() {
	}

	public Reservation(Integer capacityNumber, LocalDate date, LocalTime hour, Client client, Restaurant restaurant) {
		super();
		this.capacityNumber = capacityNumber;
		this.date = date;
		this.hour = hour;
		this.client = client;
		this.restaurant = restaurant;
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

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHour() {
		return this.hour;
	}

	public void setHour(LocalTime hour) {
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

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", capacityNumber=" + capacityNumber + ", date=" + date + ", hour=" + hour
				+ ", client=" + client + ", restaurant=" + restaurant + "]";
	}

}