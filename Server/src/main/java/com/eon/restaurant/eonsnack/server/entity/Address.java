package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "formatted")
    private String formatted;

    @Column(name = "street")
    private String street;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private long postal_code;

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "address")
    private Restaurant restaurant;

}
