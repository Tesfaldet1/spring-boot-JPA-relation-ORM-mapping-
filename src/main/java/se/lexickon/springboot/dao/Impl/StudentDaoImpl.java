package se.lexickon.springboot.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.dao.StudentDao;
import se.lexickon.springboot.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository

public class StudentDaoImpl implements StudentDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public Student persist(Student student) {
        entityManager.persist(student);
        return student;
        /*return  entityManager
                .createQuery("select s from Student s", Student.class)
                .getResultStream().findFirst();

         */
    }

    @Override
    public Optional<Student> findById(String id) {
        return   Optional.ofNullable( entityManager.find(Student.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findByEmail(String email) {
        entityManager.createQuery("select s from Student s where UPPER( s.email) =  UPPER( :e)", Student.class)
                .setParameter("e", email).getResultStream()
                .findFirst();
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByNameContains(String name) {
        entityManager.createQuery("select  s from Student s where UPPER(s.firstName) " +
                "LIKE UPPER(concat(  '%', :name, ' %'))", Student.class )
                .setParameter("name", name)
                .getResultList();
        return null;
    }

    @Override
    @Transactional
    public Student update(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public List<Student> findAll() {
        return  entityManager
                .createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void remove(String id) {
        entityManager.remove(entityManager.find(Student.class, id));
       /*Student student =  entityManager.find(Student.class, id);
        if(student != null)  entityManager.remove(id);
       // else throw new IllegalArgumentException("id was not found");

        */


    }
}
