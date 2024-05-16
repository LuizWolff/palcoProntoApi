package com.palco.palcoprontoespacolist.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="palcopronto/espaco")
public class Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "desc", nullable = false)
    private String description;

    @Column
    private String place;

    @Column(nullable = false)
    private int peopleCapacity;

    @Column
    private String disponibility;


}
