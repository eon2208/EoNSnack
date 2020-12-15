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
@Table(name = "cuisines",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
public class Cuisines implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "cuisinesList")
    Set<Restaurant> restaurant;

    @ManyToMany(mappedBy = "cuisines")
    Set<Preferences> preferences;

    public Cuisines(int id, String name){
        this.id = id;
        this.name = name;
    }
}
