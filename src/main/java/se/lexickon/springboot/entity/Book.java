package se.lexickon.springboot.entity;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "student_id")
    private  Student borrower;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getBorrower() {
        return borrower;
    }

    public void setBorrower(Student borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
