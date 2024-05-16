package com.palco.palcoprontoespacolist.boundary;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacoRequestDto {

    @NotBlank(message="Name é obrigatório")
    private String name;

    @NotBlank(message ="Description é obrigatório")
    private String description;

    @NotNull(message = "Place é obrigatório")
    private String place;

    @NotNull(message = "PeopleCapacity é obrigatório")
    private Integer peopleCapacity;

    @NotNull(message = "Disponibility é obrigatório")
    private String disponibility;
}

