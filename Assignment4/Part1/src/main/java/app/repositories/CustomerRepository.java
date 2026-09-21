package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import app.domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByPhone(String phone);
    Customer findByName(String name);
    List<Customer> findByNameOrPhone(String name, String phone);
    List<Customer>  findByCreditCardType(String cctype);


}




