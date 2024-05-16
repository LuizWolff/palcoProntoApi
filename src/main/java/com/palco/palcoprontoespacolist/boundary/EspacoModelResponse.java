package com.palco.palcoprontoespacolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EspacoModelResponse {

    private List<EspacoItem> products;

}
