package se.lexickon.springboot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "Address.findById", query = "select  a  from Address a where a.id = ?1")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false) // if you do not want to update the id
    private int id;

    @Column(nullable = false, length = 100)
    private String city;
    @Column(nullable = false, length = 100)
    private String street;
    @Column(nullable = false, length = 6)
    private String zibCode;
    @OneToOne(mappedBy = "address")
    private Student student;


    public Address() {
    }
    public Address(String city, String street, String zibCode) {
        this.city = city;
        this.street = street;
        this.zibCode = zibCode;
    }

    public Address(int id, String city, String street, String zibCode) {
        this();
        this.id = id;
        this.city = city;
        this.street = street;
        this.zibCode = zibCode;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getZibCode() {
        return zibCode;
    }

    public void setZibCode(String zibCode) {
        this.zibCode = zibCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zibCode, address.zibCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, zibCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zibCode='" + zibCode + '\'' +
                '}';
    }
}
