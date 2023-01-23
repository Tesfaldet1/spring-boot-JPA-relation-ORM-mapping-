package se.lexickon.springboot.dao;

import se.lexickon.springboot.entity.Book;
import se.lexickon.springboot.entity.Course;

import java.util.List;
import java.util.Optional;


public interface CourseDao {
    Course persist(Course course);
    Optional<Course> findById(int id);


     List<Course> findByNameContains(String name);
     Course update(Course course);
    List<Course> findAll();
     void remove(int id);

}
