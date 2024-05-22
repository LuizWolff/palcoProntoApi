package com.palco.palcoprontoespacolist.boundary;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacoItem {

    private Long id;
    private String name;
    private String place;
    private String description;
    private String disponibilidade;

}


