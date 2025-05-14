package org.mirasruntime.springjpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String login;
    String password;
    String firstName;
    String lastName;

    @Column(name = "registration_date")
    LocalDateTime registrationDate;

    @Enumerated
    UserRole role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Order> orders;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Review> reviews;
}
