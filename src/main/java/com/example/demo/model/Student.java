package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    /*
    In general, if you want to take advantage of the auto-incrementing feature of your database
    and you are confident that your code will only be run on databases that support this feature,
     you can use GenerationType.IDENTITY. If you need your code to be more portable across
     different databases, you should use GenerationType.AUTO.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    /*
        the mappedBy attribute tells Hibernate to use the student field in the
        Publication entity as the mapping to the Student entity, rather than creating
        a separate join table to represent the relationship.

        CascadeType.ALL is set on a relationship, operations that are performed on the parent entity
        (e.g. persist, remove, refresh, etc.) will also be performed on the related entities.

        orphanRemoval = true is a JPA annotation used in entity relationships to specify that
        related entities should be removed if they are no longer associated with the parent entity.
        This means that if you remove the parent entity, any related entities that have orphanRemoval = true
        set will also be removed from the database.
     */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Publication> publications;

    public Student() {
    }

    public Student(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Just create a getter for ID so that it is auto generated but not entered by the User
    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
