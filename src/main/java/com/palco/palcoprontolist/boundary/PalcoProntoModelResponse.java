package com.palco.palcoprontolist.boundary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PalcoProntoModelResponse {

    private List<PalcoProntoItem> products;

}
