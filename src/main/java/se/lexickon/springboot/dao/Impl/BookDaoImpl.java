package se.lexickon.springboot.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.dao.AddressDao;
import se.lexickon.springboot.dao.BookDao;
import se.lexickon.springboot.entity.Address;
import se.lexickon.springboot.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository

public class BookDaoImpl implements BookDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Book persist(Book book) {
        entityManager.persist(book);
        return book;

    }

    @Override
    public Optional<Book> findById(int id) {
       // return   Optional.ofNullable( entityManager.find(Address.class, id));

        // you cn use ether the upper or the secound one;

        return  entityManager.createQuery("select a from Book a where a.id=:id")
                .setParameter("id", id).getResultStream()
                .findFirst();


        /*return entityManager.createQuery("Address.findById", Address.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();

         */
    }


    @Override
    @Transactional(readOnly = true)
    public List<Book> findByNameContains(String name) {
        entityManager.createQuery("select  s from Book s where UPPER(s.name) " +
                "LIKE UPPER(concat(  '%', :name, ' %'))", Book.class )
                .setParameter("name", name)
                .getResultList();
        return null;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public List<Book> findAll() {
        return  entityManager
                .createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void remove(int id) {
        entityManager.remove(entityManager.find(Book.class, id));

        //or you can use this one
/*
       Book book =  entityManager.find(Book.class, id);
        if(book != null)  entityManager.remove(id);
        else throw new IllegalArgumentException("id was not found");

 */


    }
}
