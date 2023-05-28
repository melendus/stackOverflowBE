package com.utcn.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    //@Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "u_role")
    private String role;

    @Column(name = "u_password")
    private String password;

    @Column(name = "banned")
    private boolean banned = false;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "score")
    private Double score = 0.0D;

    @OneToMany(mappedBy = "user",
    cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Vote> votes;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {}

    public User(Long userId, String lastName, String firstName, String email, String role, String password) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

}
