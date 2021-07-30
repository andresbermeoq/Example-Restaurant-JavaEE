package ec.edu.ups.entities;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the "Client" database table.
 * 
 */
@Entity
@Table(name="\"Client\"")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String address;

	private String email;

	@Column(name="last_name")
	private String lastName;

	private String name;

	private String phone;
	
	@Column(name="id_card")
	private String idCard;

	@JsonbTransient
	@Transient
	private List<Reservation> reservations;

	public Client() {
	}

	public Client(String address, String email, String lastName, String name, String phone, String idCard) {
		super();
		this.address = address;
		this.email = email;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.idCard = idCard;
	}

	public Client(String address, String email, String lastName, String name, String phone, String idCard,
			List<Reservation> reservations) {
		super();
		this.address = address;
		this.email = email;
		this.lastName = lastName;
		this.name = name;
		this.phone = phone;
		this.idCard = idCard;
		this.reservations = reservations;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		reservation.setClient(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setClient(null);

		return reservation;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", address=" + address + ", email=" + email + ", lastName=" + lastName + ", name="
				+ name + ", phone=" + phone + ", idCard=" + idCard + ", reservations=" + reservations + "]";
	}
	
	

}