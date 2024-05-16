package com.palco.palcoprontoespacolist.boundary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class EspacoResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer peopleCapacity;
    private String disponibility;

}
