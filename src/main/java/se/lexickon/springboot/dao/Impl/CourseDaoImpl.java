package se.lexickon.springboot.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.dao.BookDao;
import se.lexickon.springboot.dao.CourseDao;
import se.lexickon.springboot.entity.Book;
import se.lexickon.springboot.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository

public class CourseDaoImpl implements CourseDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Course persist(Course course) {
        entityManager.persist(course);
        return course;

    }

    @Override
    public Optional<Course> findById(int id) {
       // return   Optional.ofNullable( entityManager.find(Address.class, id));

        // you cn use ether the upper or the secound one;

        return  entityManager.createQuery("select a from Course a where a.id=:id")
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
    public List<Course> findByNameContains(String name) {
        entityManager.createQuery("select  s from Course s where UPPER(s.name) " +
                "LIKE UPPER(concat(  '%', :name, ' %'))", Course.class )
                .setParameter("name", name)
                .getResultList();
        return null;
    }

    @Override
    @Transactional
    public Course update(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public List<Course> findAll() {
        return  entityManager
                .createQuery("select c from Book c", Course.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void remove(int id) {
        entityManager.remove(entityManager.find(Course.class, id));

        //or you can use this one
/*
       Book book =  entityManager.find(Book.class, id);
        if(book != null)  entityManager.remove(id);
        else throw new IllegalArgumentException("id was not found");

 */


    }
}
