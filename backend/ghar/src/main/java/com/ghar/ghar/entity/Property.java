package com.ghar.ghar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private BigDecimal price;

    private String address;
    private String city;
    private String state;
    private String zipCode;

    private int bedrooms;
    private int bathrooms;
    private double size;
    private String amenities;
    private String propertyType;
    private String status;
    @OneToMany(mappedBy = "property")
    private List<Image> images;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    private LocalDateTime dateAdded;

}
