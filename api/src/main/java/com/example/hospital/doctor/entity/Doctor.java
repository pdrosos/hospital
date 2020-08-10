package com.example.hospital.doctor.entity;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.hospital.common.entity.AuditableEntity;
import com.example.hospital.common.entity.Gender;

@Entity
@Table(name="doctors")
public class Doctor extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    private String title;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate dateOfBirth;

    private String biography;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
