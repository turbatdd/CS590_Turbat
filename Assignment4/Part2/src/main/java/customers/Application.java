package customers;

import customers.domain.Address;
import customers.domain.CreditCard;
import customers.domain.Customer;
import customers.domain.Student;
import customers.repository.CustomerRepository;
import customers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private StudentRepository studentrepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findCustomerWithCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		System.out.println("-----------find by name ----------------");
		System.out.println(customerRepository.findByName("John doe"));


		// create 5 students
		Student student = new Student(101, "John Doe", "0622341678", "johnd@gmail.com");
		Address address = new Address("123 Main St", "New York", "10001");
		student.setAddress(address);
		studentrepository.save(student);

		student = new Student(109, "John Jones", "0624321234", "jones@gmail.com");
		address = new Address("456 Oak Ave", "Chicago", "60601");
		student.setAddress(address);
		studentrepository.save(student);

		student = new Student(66, "James Johnson", "068633452", "jj123@gmail.com");
		address = new Address("789 Pine Rd", "New York", "10002");
		student.setAddress(address);
		studentrepository.save(student);

		student = new Student(202, "John Doe", "065544332", "jdoe2@gmail.com");
		address = new Address("101 Elm St", "Seattle", "98101");
		student.setAddress(address);
		studentrepository.save(student);

		student = new Student(305, "Diana Prince", "061122334", "diana@gmail.com");
		address = new Address("202 Maple Dr", "Chicago", "60602");
		student.setAddress(address);
		studentrepository.save(student);

		System.out.println("----------- All students ----------------");
		System.out.println(studentrepository.findAll());

		System.out.println("----------- find by name 'John Doe' ----------------");
		List<Student> studentsByName = studentrepository.findByName("John Doe");
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
