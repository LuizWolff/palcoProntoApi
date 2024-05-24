package com.palco.palcoprontoespacolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "eventos")
public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Normal, VIP

    @Column(name = "desc", nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "id_espaco")
    private Espaco espaco; // Usando a classe Espaco diretamente

    @OneToMany
    @JoinColumn(name = "id_evento")
    private List<Ingresso> ingressos;
}