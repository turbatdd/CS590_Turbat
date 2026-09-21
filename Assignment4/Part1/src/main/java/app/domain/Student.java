package app.domain;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String phoneNumber;
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	public Student() {
		// Required by JPA
	}

	public Student(String name, String phoneNumber, String email, Address address) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}

	// Getters and Setters
	public Long getId() { return id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }

	@Override
	public String toString() {
		return String.format("Student [ID=%d, Name='%s', Phone='%s', Email='%s', Address='%s']",
				id, name, phoneNumber, email, address);
	}
}
