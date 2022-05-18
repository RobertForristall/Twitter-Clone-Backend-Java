/*
* Written by Robert Forristall
* */

package com.shareclub.TwitterCloneBackendJava.user;

import com.shareclub.TwitterCloneBackendJava.tweet.Tweet;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/*
* User class used to create a table in database
*
* Attributes:
* - Id: long, used as the primary key of the table
* - email: String, Unique, NotNull
* - name: String, NotNull
* - dob: Date, NotNull
* - is_verified: boolean, NotNull
* - pass: String, NotNull
* - tweets: List<Tweets>, foreign key for tweets table
* - age: int, Transient, used for age verification
*
* Constructors:
* - User(): Empty constructor used with JPA
* - User(
*       String email, String name, LocalDate dob, boolean is_verified, String pass
*   ): Create bases user with all needed fields
*
* Methods:
* - Getters & Setters for all attributes
* - ToString: return the user as a string
* */
@Entity
@Table
public class User {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private boolean is_verified;

    @Column(nullable = false)
    private String pass;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> tweets = new ArrayList<>();

    @Transient
    private Integer age;

    public User() {
    }

    public User(String email, String name, LocalDate dob, boolean is_verified, String pass) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.is_verified = is_verified;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", is_verified=" + is_verified +
                ", pass='" + pass + '\'' +
                ", age=" + age +
                '}';
    }
}
