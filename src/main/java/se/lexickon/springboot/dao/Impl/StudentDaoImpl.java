package se.lexickon.springboot.dao.Impl;

import org.springframework.stereotype.Repository;
import se.lexickon.springboot.dao.StudentDao;
import se.lexickon.springboot.model.Student;

import java.util.List;
import java.util.Optional;

@Repository

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student persist(Student student) {
        return null;
    }

    @Override
    public Optional<Student> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Student> findByNameContains() {
        return null;
    }

    @Override
    public Student update(Student student) {
        return null;
    }

    @Override
    public void remove(String id) {

    }
}
