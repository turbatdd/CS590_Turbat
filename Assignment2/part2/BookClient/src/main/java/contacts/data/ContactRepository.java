package contacts.data;

import contacts.Contacts;
import contacts.domain.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ContactRepository {
    private Map<String, Contact> contactData = new HashMap<>();

    public void addContact(Contact contact) {
        contactData.put(contact.getEmail(), contact);
    }

    public void updateContact(Contact contact) {
        contactData.put(contact.getEmail(), contact);
    }

    public void deleteContact(String email) {
        contactData.remove(email);
    }

    public Contact getContact(String email) {
        return contactData.get(email);
    }

    public Collection<Contact> getAllContacts() {
        return contactData.values();
    }

}
