package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "amount")
    private double amount;

    @OneToOne
    @JoinColumn(name = "orderHeader_id")
    private OrderHeader orderHeader;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_type")
    private PaymentType paymentTypes;


}
