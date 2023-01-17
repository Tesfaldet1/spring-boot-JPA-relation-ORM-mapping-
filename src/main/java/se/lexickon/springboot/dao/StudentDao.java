package se.lexickon.springboot.dao;

import se.lexickon.springboot.entity.Student;

import java.util.List;
import java.util.Optional;


public interface StudentDao {
    Student persist(Student student);
    Optional<Student> findById(String id);
     Optional <Student> findByEmail(String email);

     List<Student> findByNameContains(String name);
     Student update(Student student);
    List<Student> findAll();
     void remove(String id);

}
