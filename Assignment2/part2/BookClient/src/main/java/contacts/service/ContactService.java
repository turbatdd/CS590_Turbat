package contacts.service;

import contacts.data.ContactRepository;
import contacts.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void addContact(Contact contact) {
        contactRepository.addContact(contact);
    }

    public void updateContact(Contact contact) {
        contactRepository.updateContact(contact);
    }

    public void deleteContact(String email) {
        contactRepository.deleteContact(email);
    }

    public Contact getContact(String email) {
        return contactRepository.getContact(email);
    }

    public Collection<Contact> getAllContacts() {
        return contactRepository.getAllContacts();
    }

}
