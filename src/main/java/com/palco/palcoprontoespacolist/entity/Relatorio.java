package com.palco.palcoprontoespacolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "relatorios")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Eventos eventos;

    @ManyToOne
    @JoinColumn(name = "id_espaco")
    private Espaco espaco;

    @Column(name = "preco_total")
    private BigDecimal precoTotal;

}

