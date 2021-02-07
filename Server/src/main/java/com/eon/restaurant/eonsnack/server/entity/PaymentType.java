package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_type",uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "paymentTypes")
    private Set<Payment> payments;
}
