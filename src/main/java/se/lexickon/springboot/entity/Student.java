
package se.lexickon.springboot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "DB_Student")
public class Student {
   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false) // if you do not want to update the id

    */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    private String studentId;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false)
    private LocalDate birthDate;
    private boolean status;
    private LocalDateTime registrationDate;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy ="borrower",orphanRemoval = true )
    private List<Book> borrowedBooks;
    @ManyToMany(mappedBy = "participant")
    private List<Course> enrRolledCourses;

    public Student() {
        this.status = true;
        this.registrationDate = LocalDateTime.now();
    }

    public Student( String firstName, String lastName, String email, LocalDate birthDate) {
        this();
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }
    public Student( String firstName, String lastName, String email, LocalDate birthDate,Address address) {
        this();
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        setAddress(address);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStatus() {
        return status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        //bi directional
        if(address !=null)address.setStudent(this);
        this.address = address;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    public  void borrowBook(Book book){
        if(book ==null) throw new IllegalArgumentException("the book data was null");
        if (borrowedBooks == null )borrowedBooks = new ArrayList<>();
        borrowedBooks.add(book);
        book.setBorrower(this); //bi directional

    }
    public  void returnBook(Book book){
        if(book ==null) throw new IllegalArgumentException("the book data was null");
        if (borrowedBooks != null ){
            book.setBorrower(null);
            borrowedBooks.remove(book);

        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Course> getEnrRolledCourses() {
        if (enrRolledCourses == null) enrRolledCourses = new ArrayList<>();
        return enrRolledCourses;
    }

    public void setEnrRolledCourses(List<Course> enrRolledCourses) {
        this.enrRolledCourses = enrRolledCourses;
    }
    public void enrollCourse(Course course){
        if(course == null) throw new IllegalArgumentException("the course data was null");
        if(enrRolledCourses == null) enrRolledCourses =new ArrayList<>();
        enrRolledCourses.add(course);
        course.getParticipant().add(this);

    }
    public void unEnrollCourse(Course course){
        if(course == null) throw new IllegalArgumentException("the course data was null");
        if(enrRolledCourses!=null){
            course.getParticipant().remove(this);
            enrRolledCourses.remove(course);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId && status == student.status && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(birthDate, student.birthDate) && Objects.equals(registrationDate, student.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, email, birthDate, status, registrationDate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", status=" + status +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
