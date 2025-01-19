package com.BE.Entity;

import com.BE.Common.Gender;
import com.BE.Common.LoginMethod;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String occupation;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime editedDate;

    @Enumerated(EnumType.STRING)
    private LoginMethod loginMethod;

    //External login (current x)
    //private String externalId;

    // Getters, Setters, Constructors
    public UserEntity() {}

    public UserEntity(String nickname, String email, String password, Integer age, Gender gender, String occupation, LoginMethod loginMethod) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.loginMethod = loginMethod;

        //External login (current x)
        //this.externalId = externalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public LoginMethod getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(LoginMethod loginMethod) {
        this.loginMethod = loginMethod;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getEditedDate() {
        return editedDate;
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.editedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.editedDate = LocalDateTime.now();
    }

    //외부 로그인 (지금은 x)
//    public String getExternalId() {
//        return externalId;
//    }
//
//    public void setExternalId(String externalId) {
//        this.externalId = externalId;
//    }
}
