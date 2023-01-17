package se.lexickon.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexickon.springboot.dao.StudentDao;
import se.lexickon.springboot.entity.Student;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    @Autowired
    StudentDao studentDao;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("######################");
        Student createdStudent1 =  studentDao.persist(new Student("Natu",
                "Kahase", "natu@gmail.com",
                LocalDate.parse("2002-06-24")));
        Student createdStudent2 =  studentDao.persist(new Student("Tesfaldet",
                "Weldemicheal", "tweldemicheal@gmail.com",
                LocalDate.parse("1989-12-10")));
        System.out.println("###################"+ createdStudent1.getStudentId());

    }



}
