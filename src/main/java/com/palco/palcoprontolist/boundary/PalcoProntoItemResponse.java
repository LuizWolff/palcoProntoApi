package com.palco.palcoprontolist.boundary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PalcoProntoItemResponse {

    private Long id;
    private String name;
    private String description;


}
