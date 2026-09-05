package contacts.data;

import contacts.domain.Contact;
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

    public void deleteContact(String firstName) {
        contactData.remove(firstName);
    }

    public Contact getContact(String firstName) {
        return contactData.get(firstName);
    }

    public Collection<Contact> getAllContacts() {
        return contactData.values();
    }

}
