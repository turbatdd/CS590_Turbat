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

    @PutMapping("/{email}")
    public void updateBook(@PathVariable String email, @RequestBody Contact contact) {
        contact.setEmail(email);
        contactService.updateContact(contact);
    }

    @DeleteMapping("/{email}")
    public void deleteBook(@PathVariable String email) {
        contactService.deleteContact(email);
    }

    @GetMapping("/{email}")
    public Contact getBook(@PathVariable String email) {
        return contactService.getContact(email);
    }

    @GetMapping
    public Collection<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}

