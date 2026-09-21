package app;

import java.util.List;

import app.domain.Address;
import app.domain.CreditCard;
import app.domain.Student;
import app.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.repositories.CustomerRepository;
import app.domain.Customer;

@SpringBootApplication
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;
	@Autowired
	StudentRepository studentrepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerrepository.save(customer);

//get customers
		System.out.println(customerrepository.findById(66).get());
		System.out.println(customerrepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerrepository.findAll());
		//update customer
		customer = customerrepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerrepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerrepository.findByPhone("0622341678"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerrepository.findByCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerrepository.findByName("John doe"));



//Create student
		Address address = new Address("123 Main St", "New York", "10001");
		Student student = new Student("Alice Smith", "0622341678", "alice@gmail.com", address);
		studentrepository.save(student);

		address = new Address("456 Oak Ave", "Chicago", "60601");
		student = new Student("Bob Jones", "0624321234", "bob@gmail.com", address);
		studentrepository.save(student);

		address = new Address("789 Pine Rd", "New York", "10002");
		student = new Student("Turbat Davaa", "068633452", "tuuruu@gmail.com", address);
		studentrepository.save(student);

		address = new Address("101 Elm St", "Seattle", "98101");
		student = new Student("Charlie Brown", "065544332", "charlie@gmail.com", address);
		studentrepository.save(student);

		address = new Address("202 Maple Dr", "Chicago", "60602");
		student = new Student("Diana Prince", "061122334", "diana@gmail.com", address);
		studentrepository.save(student);

		System.out.println("----------- All students ----------------");
		System.out.println(studentrepository.findAll());

		System.out.println("----------- find by name 'Turbat Davaa' ----------------");
		List<Student> studentsByName = studentrepository.findByName("Turbat Davaa");
		for (Student st : studentsByName) {
			System.out.println(st);
		}

		System.out.println("----------- find by phone '0622341678' ----------------");
		System.out.println(studentrepository.findByPhoneNumber("0622341678"));

		System.out.println("----------- find students from city 'Chicago' ----------------");
		List<Student> studentsByCity = studentrepository.findByAddressCity("Chicago");
		for (Student st : studentsByCity) {
			System.out.println(st);
		}
	}

}
