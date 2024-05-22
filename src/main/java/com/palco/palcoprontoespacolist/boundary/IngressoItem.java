package com.palco.palcoprontoespacolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngressoItem {
    private Long id;
    private String tipo;
    private double preco;
    private int quantidadeDisponivel;
}