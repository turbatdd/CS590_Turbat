package com.cs590.webshop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "suppliers")
public class Supplier {

    @Id
    private final String supplierId;

    @Field("name")
    private String name;

    @Field("contact_email")
    private String contactEmail;

    public Supplier(String supplierId, String name, String contactEmail) {
        if (supplierId == null || supplierId.isBlank()) {
            throw new IllegalArgumentException("Supplier ID cannot be empty.");
        }
        this.supplierId = supplierId;
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public void updateContactInfo(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(supplierId, supplier.supplierId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId);
    }
}