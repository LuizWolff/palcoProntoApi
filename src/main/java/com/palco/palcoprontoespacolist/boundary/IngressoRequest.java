package com.palco.palcoprontoespacolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngressoRequest {
    private String tipo;
    private double preco;
    private int quantidadeDisponivel;
}