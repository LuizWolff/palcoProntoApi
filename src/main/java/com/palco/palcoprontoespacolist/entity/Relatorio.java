package com.palco.palcoprontoespacolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_espaco")
    @Fetch(FetchMode.JOIN)
    private Espaco espaco;

    @Column
    private String disponibilityEspaco;

    @Column
    private BigDecimal precoTotalVendidoIngresso;

}

