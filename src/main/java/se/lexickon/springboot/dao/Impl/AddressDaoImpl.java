package se.lexickon.springboot.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.dao.AddressDao;
import se.lexickon.springboot.dao.StudentDao;
import se.lexickon.springboot.entity.Address;
import se.lexickon.springboot.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository

public class AddressDaoImpl implements AddressDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Address persist(Address address) {
        entityManager.persist(address);
        return address;

    }

    @Override
    public Optional<Address> findById(int id) {
       // return   Optional.ofNullable( entityManager.find(Address.class, id));

        // you cn use ether the upper or the secound one;

        return  entityManager.createQuery("select a from Address a where a.id=:id")
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
    public List<Address> findByNameContains(String name) {
        entityManager.createQuery("select  s from Address s where UPPER(s.city) " +
                "LIKE UPPER(concat(  '%', :name, ' %'))", Address.class )
                .setParameter("name", name)
                .getResultList();
        return null;
    }

    @Override
    @Transactional
    public Address update(Address address) {
        return entityManager.merge(address);
    }

    @Override
    public List<Address> findAll() {
        return  entityManager
                .createQuery("select a from Student a", Address.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void remove(int id) {
        //entityManager.remove(entityManager.find(Address.class, id));

        //or you can use this one

       Address address =  entityManager.find(Address.class, id);
        if(address != null)  entityManager.remove(id);
        else throw new IllegalArgumentException("id was not found");


    }
}
