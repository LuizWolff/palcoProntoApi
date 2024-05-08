package com.palco.palcoprontolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class PalcoProntoResponseDto {

    private Long id;
    private String name;
    private String descricao;
    private Integer stock;

}
