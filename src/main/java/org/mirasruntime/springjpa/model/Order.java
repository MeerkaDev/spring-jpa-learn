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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Enumerated
    OrderStatus status;

    String address;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    List<ProductInOrder> productsInOrder;
}
