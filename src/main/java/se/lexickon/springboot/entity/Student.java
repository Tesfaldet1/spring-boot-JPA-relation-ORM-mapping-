
package se.lexickon.springboot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
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
