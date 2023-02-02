package com.example.demo.dto;

public class PublicationDTO {
    private String title;
    private Integer year;
    private String firstName;
    private String lastName;
    private String email;

    public PublicationDTO(String title, Integer year, String firstName, String lastName, String email) {
        this.title = title;
        this.year = year;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
