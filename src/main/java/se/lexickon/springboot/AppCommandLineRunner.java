package se.lexickon.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexickon.springboot.dao.AddressDao;
import se.lexickon.springboot.dao.BookDao;
import se.lexickon.springboot.dao.CourseDao;
import se.lexickon.springboot.dao.StudentDao;
import se.lexickon.springboot.entity.Address;
import se.lexickon.springboot.entity.Book;
import se.lexickon.springboot.entity.Course;
import se.lexickon.springboot.entity.Student;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    @Autowired
    StudentDao studentDao;
    @Autowired
    AddressDao addressDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    CourseDao courseDao;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        ex5();


    }
    public  void ex1(){
        Address addressData = new Address("Jönköping", "Råslätt", "66512");
        Address createdAddress = addressDao.persist(addressData);

        Student studentData  = new Student("Natu", "Kahase", "natu@gmail.com",
                LocalDate.parse("2002-06-24"), createdAddress);
        studentData.setAddress(createdAddress);
        Student  createdStudent  = studentDao.persist(studentData);
        studentDao.remove(createdStudent.getStudentId());


    }
    public void ex2(){
        Student studentData = new Student ("Tesfaldet", "Weldemicheal",
                "tweldemicheal@gmail.com", LocalDate.parse("1989-12-10"),
                new Address("Jönköping", "Råsllet", "55632"));
       Student createdStudent =  studentDao.persist(studentData);
       studentDao.remove(createdStudent.getStudentId());

    }

    public void ex3(){
        Student studentData = new Student ("Tesfaldet", "Weldemicheal",
                "tweldemicheal@gmail.com", LocalDate.parse("1989-12-10"),
                new Address("Jönköping", "Råsllet","55632"));
        Student createdStudent = studentDao.persist(studentData);
        Optional<Address> optionalAddress = addressDao.findById(1);
        if (optionalAddress.isPresent()) {
            Address address =optionalAddress.get();
            System.out.println("________-_--__");
            System.out.println(address);
            System.out.println("___________________");
            System.out.println(address.getStudent());
            System.out.println("_______________________________");
        }


    }

public  void ex4(){
    Student studentData = new Student ("Tesfaldet", "Weldemicheal",
            "tweldemicheal@gmail.com", LocalDate.parse("1989-12-10"),
            new Address("Jönköping", "Råsllet","55632"));
    Student createdStudent = studentDao.persist(studentData);


    Book JavaBookData = new Book("Java how to program");
    Book  MicrosoftBookData = new Book("Microsoft Azure Administrator Exam");

    JavaBookData.setBorrower(createdStudent);
    MicrosoftBookData.setBorrower(createdStudent);

    Book createdJavaBookData = bookDao.persist(JavaBookData);
   Book createdMicBook =  bookDao.persist(MicrosoftBookData);



   createdStudent.borrowBook(createdMicBook);
   createdStudent.returnBook(createdJavaBookData);
   Optional <Student> optionalStudent = studentDao.findById(createdStudent.getStudentId());
    if(optionalStudent.isPresent()){
        System.out.println(optionalStudent.get().getBorrowedBooks().size());

    }

    //studentDao.remove(createdStudent.getStudentId());

}
public void ex5(){
    Course  DataBaseAdministrator = courseDao.persist(new Course("DataBaseAdministrator"));
    Course Java = courseDao.persist(new Course("java"));
    Course Python = courseDao.persist(new Course("Python"));

    Student studentData = new Student ("Tesfaldet", "Weldemicheal",
            "tweldemicheal@gmail.com", LocalDate.parse("1989-12-10"),
            new Address("Jönköping", "Råsllet", "55632"));


    Student createdStudent =  studentDao.persist(studentData);
    Python.addStudent(createdStudent);

}

}
