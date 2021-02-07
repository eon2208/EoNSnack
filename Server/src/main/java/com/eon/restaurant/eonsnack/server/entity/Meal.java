package com.eon.restaurant.eonsnack.server.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meal")
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description",
            length = 500)
    private String description;

    @OneToMany(mappedBy = "meal")
    private Set<OrderDetails> orderDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tags tags;
}
