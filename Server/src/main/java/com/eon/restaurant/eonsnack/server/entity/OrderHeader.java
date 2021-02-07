package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_header")
public class OrderHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column(name = "delivery_cost")
    private Double deliveryCost;

    @Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "orderHeader")
    private Set<OrderDetails> orderDetails;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocation")
    private Geolocation geolocation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment")
    private Payment payment;
}
