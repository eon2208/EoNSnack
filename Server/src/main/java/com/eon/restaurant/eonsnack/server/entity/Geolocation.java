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
@Table(name = "geolocation",uniqueConstraints = {
        @UniqueConstraint(columnNames = "longitude"),
        @UniqueConstraint(columnNames = "latitude")
})
public class Geolocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "longitude")
    private double lon;

    @Column(name = "latitude")
    private double lat;

}
