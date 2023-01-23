package se.lexickon.springboot.dao;

import se.lexickon.springboot.entity.Address;
import se.lexickon.springboot.entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookDao {
    Book persist(Book book);
    Optional<Book> findById(int id);


     List<Book> findByNameContains(String name);
     Book update(Book book);
    List<Book> findAll();
     void remove(int id);

}
