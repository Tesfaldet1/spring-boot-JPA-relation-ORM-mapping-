package se.lexickon.springboot.dao;

import se.lexickon.springboot.entity.Address;
import se.lexickon.springboot.entity.Student;

import java.util.List;
import java.util.Optional;


public interface AddressDao {
    Address persist(Address address);
    Optional<Address> findById(int id);


     List<Address> findByNameContains(String name);
     Address update(Address address);
    List<Address> findAll();
     void remove(int id);

}
