package com.example.demo.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    JoinColumn annotation tells Hibernate to create a foreign key column in the publication
     table with the name student_id, which refers to the primary key column id in the student table.

     JsonIgnore annotation ignores the student object when serializing. This helps when getting a
     list of all publications without the students
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private Integer year;

    public Publication() {
    }

    public Publication(Student student, String title, Integer year) {
        this.student = student;
        this.title = title;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
