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
@Table(name = "restaurant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "restaurant_number")
        })
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Column(name = "restaurant_name")
    private String restName;

    @Column(name = "hours")
    private String hours;

    @Column(name = "restaurant_number")
    private String restNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocation_id")
    private Geolocation geolocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "restaurant_tags",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tags> tagsList;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "restaurant")
    private Set<Meal> meals;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "restaurant_cuisines",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id"))
    private Set<Cuisines> cuisinesList;

    public Restaurant(long id ,String restName, Set<Cuisines> cuisines){
        this.id = id;
        this.restName = restName;
        this.cuisinesList = cuisines;
    }

}
