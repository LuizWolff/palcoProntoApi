package com.palco.palcoprontoespacolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingresso_id", nullable = false)
    private Ingresso ingresso;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String tipoDeIngresso; // Normal, VIP
}
