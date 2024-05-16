package com.palco.palcoprontoespacolist.boundary;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacoItemResponse {

    private Long id;
    private String name;
    private String description;
    private String disponibility;


}
