package com.palco.palcoprontoespacolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacoItem {

    private Long id;
    private String name;
    private String description;
    private String disponibilidade;

}


