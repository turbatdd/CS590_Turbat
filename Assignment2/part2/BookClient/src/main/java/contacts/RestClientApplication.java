package contacts;

import contacts.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String serverUrl = "http://localhost:8080/contacts";
		String frankEmail = "fbrowns@acme.com";
		String johnEmail = "jdoe@acme.com";

		// add Frank
		restTemplate.postForLocation(serverUrl, new Contact("Frank","Browns", frankEmail,
				"0639332163"));
		// add John
		restTemplate.postForLocation(serverUrl, new Contact("John","Doe", johnEmail,
				"6739127563"));
		// get frank
		Contact contact= restTemplate.getForObject(serverUrl+"/{email}", Contact.class, frankEmail);
		System.out.println("----------- get Frank-----------------------");
		System.out.println(contact.getFirstName()+" "+contact.getLastName());
        // get all
		Contact[] contacts= restTemplate.getForObject(serverUrl, Contact[].class);
		System.out.println("----------- get all contacts-----------------------");
		System.out.println(java.util.Arrays.toString(contacts));

		// delete John
		restTemplate.delete(serverUrl+"/{email}", johnEmail);

		// update frank
		contact.setEmail("franky@gmail.com");
		restTemplate.put(serverUrl+"/{email}", contact, contact.getEmail());

		// get all
		contacts= restTemplate.getForObject(serverUrl, Contact[].class);
		System.out.println("----------- get all contacts-----------------------");
		System.out.println(java.util.Arrays.toString(contacts));
	}


	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
