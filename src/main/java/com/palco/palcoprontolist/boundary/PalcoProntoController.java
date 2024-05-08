package com.palco.palcoprontolist.boundary;

import com.palco.palcoprontolist.control.PalcoProntoService;
import com.palco.palcoprontolist.entity.PalcoPronto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
// Com essa anotação a classe já consegue receber aquisições do POSTMAN/Front-END
// O Controller recebe as aquisições do front-end/clientes
public class PalcoProntoController {

    private final List<PalcoPronto> palcoProntoList;
    private final ModelMapper modelMapper;
    private static Long CONTADOR = 2L;
    private final PalcoProntoService palcoProntoService;


    public PalcoProntoController(List<PalcoPronto> palcoProntoList, ModelMapper modelMapper, PalcoProntoService palcoProntoService) {
            this.palcoProntoList = palcoProntoList;
        this.modelMapper = modelMapper;
        this.palcoProntoService = palcoProntoService;
    }

    @GetMapping("/palcopronto") //URI
    public List<PalcoProntoResponseDto> getPalcos(){
        List<PalcoProntoResponseDto> palcoProntoResponseDtos = palcoProntoService.pegarPalco();

        return palcoProntoResponseDtos;
    }

    @GetMapping("/palcopronto/{id}")
    public PalcoProntoResponseDto getPalcoPorId(@PathVariable Long id) {
        for (PalcoPronto palcoPronto : palcoProntoList) {
            if (palcoPronto.getId().equals(id)) {
                return modelMapper.map(palcoPronto, PalcoProntoResponseDto.class);
            }
        }
        return null;
    }

    @GetMapping("/palcopronto/descricao")
    public List<PalcoPronto> getPalcoPorDescricao(@RequestParam String desc) {
        List palcos = palcoProntoList.stream().filter(palcoPronto -> palcoPronto.getDescricao().contains(desc)).toList();

        return palcos;
    }


    @PostMapping("/palcopronto")
    @ResponseStatus(HttpStatus.CREATED)
    public PalcoProntoResponseDto criarPalco(@Valid @RequestBody PalcoProntoRequestDto palcoProntoRequestDto) {
      PalcoProntoResponseDto palcoProntoDto = palcoProntoService.savePalco(palcoProntoRequestDto);

        return palcoProntoDto;

    }

    @DeleteMapping("/palcopronto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePalco(@PathVariable @Valid @NotNull Long id){
        palcoProntoService.apagarPalcoPorId(id);
    }

    @PutMapping("/palcopronto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PalcoProntoResponseDto editarPalco(@PathVariable Long id, @Valid @RequestBody PalcoProntoRequestDto requestDto){

        PalcoProntoResponseDto palcoProntoResponseDto = palcoProntoService.editarPalco(id, requestDto);

        return palcoProntoResponseDto;
    }

    @PatchMapping("/palcopronto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarParcialmentePalco(@PathVariable Long id, @RequestBody PalcoProntoRequestDto requestDto){

        palcoProntoService.editarParcilamente(id, requestDto);

    }

}
