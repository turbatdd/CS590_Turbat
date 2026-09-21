package customers.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
	@Id
	private int studentId;
	private String name;
	private String phoneNumber;
	private String email;
	private Address address;

	public Student(int studentId, String name, String phoneNumber, String email) {
		this.studentId = studentId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student{" +
				"studentId=" + studentId +
				", name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", address=" + address +
				'}';
	}

	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}