package com.palco.palcoprontolist.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="palcopronto")
public class PalcoPronto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "desc", nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double price;

    @Column
    private String place;

    @Column(nullable = false)
    private int stock;

}
