package se.lexickon.springboot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany()
    private List<Student> participant;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(int id, String name) {
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

    public List<Student> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Student> participant) {
        this.participant = participant;
    }
    public  void addStudent(Student student){
        if(student== null) throw  new IllegalArgumentException("the course data was null");
        if (participant == null ) participant = new ArrayList<>();
        participant.add(student);
        student.getEnrRolledCourses().add(this);

    }



    public  void removeStudent(Student student){
        if(student== null) throw  new IllegalArgumentException("the course data was null");
        student.getEnrRolledCourses().remove(this);
        participant.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
