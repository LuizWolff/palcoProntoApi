package com.palco.palcoprontoespacolist.boundary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarEventoRequest {

    private String name;
    private String description;
    private LocalDateTime dateTime;
    private Long idEspaco;
    private List<Long> idsIngressos;


}
