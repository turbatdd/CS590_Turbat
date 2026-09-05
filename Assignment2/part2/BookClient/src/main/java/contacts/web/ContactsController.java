package contacts.web;

import contacts.domain.Contact;
import contacts.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/contacts")
public class ContactsController {
    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public void addContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @PutMapping("/{firstName}")
    public void updateBook(@PathVariable String firstName, @RequestBody Contact contact) {
        contact.setFirstName(firstName);
        contactService.updateContact(contact);
    }

    @DeleteMapping("/{firstName}")
    public void deleteBook(@PathVariable String firstName) {
        contactService.deleteContact(firstName);
    }

    @GetMapping("/{firstName}")
    public Contact getBook(@PathVariable String firstName) {
        return contactService.getContact(firstName);
    }

    @GetMapping
    public Collection<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}

