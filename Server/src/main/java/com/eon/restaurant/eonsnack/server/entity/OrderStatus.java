package com.eon.restaurant.eonsnack.server.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "oder_status",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
})
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;
}
