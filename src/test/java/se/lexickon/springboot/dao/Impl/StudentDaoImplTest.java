package se.lexickon.springboot.dao.Impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.entity.Student;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class StudentDaoImplTest {
    @Autowired
    TestEntityManager em;
    @Autowired
    StudentDaoImpl testObject;
    String createdStudentId1;
    String createdStudentId2;
    @BeforeEach
    public  void setup(){
        Student studentData1 = new Student("Tesfaldet",
                "Weldemicheal", "tweldemicheal@gmail", LocalDate.parse("2023-01-14"));

        Student studentData2  = new Student("bob", "MariamAdey",
                "bob@gmail", LocalDate.parse("2023-01-12"));

        Student createdStudent1 = em.persist(studentData1);
        Student createdStudent2 = em.persist(studentData2);

        createdStudentId1 = createdStudent1.getStudentId();
        createdStudentId2 = createdStudent2.getStudentId();
    }
    @Test
    public  void persist(){
        Student studentData = new Student("Natu", "Kahase", "Natu@gmail", LocalDate.parse("2002-06-24"));
        Student createdStudent = testObject.persist(studentData);

        assertNotNull(createdStudent);
        assertNotNull(createdStudent.getStudentId());


    }
}
