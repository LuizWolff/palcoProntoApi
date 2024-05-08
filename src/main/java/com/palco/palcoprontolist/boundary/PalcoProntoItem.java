package com.palco.palcoprontolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PalcoProntoItem {

    private Long id;
    private String name;
    private String description;

}


