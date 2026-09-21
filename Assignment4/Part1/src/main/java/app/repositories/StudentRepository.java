package app.repositories;

import app.domain.Customer;
import app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
    Student findByPhoneNumber(String phoneNumber);
    List<Student> findByAddressCity(String city);
}




