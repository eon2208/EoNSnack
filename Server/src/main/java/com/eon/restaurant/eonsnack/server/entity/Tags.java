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
@Table(name = "tags",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tagsList")
    Set<Restaurant> restaurants;

    @ManyToMany(mappedBy = "tags")
    Set<Preferences> preferences;

    @OneToMany(mappedBy = "tags",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Meal> meals;

}
