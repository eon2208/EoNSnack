package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal")
    private Meal meal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_header")
    private OrderHeader orderHeader;

}
