package com.palco.palcoprontolist.boundary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PalcoProntoRequestDto {

    @NotBlank(message="Nome é obrigatorio")
    private String name;

    @NotBlank(message ="Descrição é obrigatorio")
    private String descricao;

    @NotNull(message = "Price é obrigatorio")
    private Double price;

    @NotNull(message = "Place é obrigatorio")
    private String place;

    @NotNull(message = "Stock é obrigatorio")
    private Integer stock;

}

