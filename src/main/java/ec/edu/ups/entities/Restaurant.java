package ec.edu.ups.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "Restaurant" database table.
 * 
 */
@Entity
@Table(name="\"Restaurant\"")
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	@Column(name="capacity_number")
	private Integer capacityNumber;

	private String name;

	private String phone;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="restaurant")
	private List<Reservation> reservations;

	public Restaurant() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCapacityNumber() {
		return this.capacityNumber;
	}

	public void setCapacityNumber(Integer capacityNumber) {
		this.capacityNumber = capacityNumber;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setRestaurant(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setRestaurant(null);

		return reservation;
	}

}