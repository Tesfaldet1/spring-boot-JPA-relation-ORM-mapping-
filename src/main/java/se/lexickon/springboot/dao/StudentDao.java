package se.lexickon.springboot.dao;

import se.lexickon.springboot.model.Student;

import java.util.List;
import java.util.Optional;


public interface StudentDao {
    Student persist(Student student);
    Optional<Student> findById(String id);
     Optional <Student> findByEmail(String email);

     List<Student> findByNameContains();
     Student update(Student student);
     void remove(String id);

}
