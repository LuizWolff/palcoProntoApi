package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.EspacoRepository;
import com.palco.palcoprontoespacolist.control.EspacoService;
import com.palco.palcoprontoespacolist.entity.Espaco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/palcopronto")
// Na classe controler pode fazer um @REQUESTMAPPING e essa anotação em vez de estar em todos os
// metodos do /palcopronto, posso apenas colocar nesta anotação.

// Com essa anotação a classe já consegue receber aquisições do POSTMAN/Front-END
// O Controller recebe as aquisições do front-end/clientes
public class EspacoController {
    @Autowired
    private EspacoRepository espacoRepository;


    private final List<Espaco> espacoList;
    private final ModelMapper modelMapper;
    private static Long CONTADOR = 2L;
    private final EspacoService espacoService;


    public EspacoController(List<Espaco> palcoProntoList, ModelMapper modelMapper, EspacoService palcoProntoService) {
            this.espacoList = palcoProntoList;
        this.modelMapper = modelMapper;
        this.espacoService = palcoProntoService;
    }

    @GetMapping("/espaco") //URI
    public List<EspacoResponseDto> getEspaco(){
        List<EspacoResponseDto> espacoResponseDtos = espacoService.getEspaco();

        return espacoResponseDtos;
    }

    @GetMapping("/espaco/{id}")
    public EspacoResponseDto getEspacoPorId(@PathVariable Long id) {
        Espaco espaco = espacoRepository.findById(id).orElse(null);
        if (espaco != null) {
            return modelMapper.map(espaco, EspacoResponseDto.class);
        } else {
            return null;
        }
    }

    @GetMapping("/espaco/description")
    public List<Espaco> getEspacoPorDescricao(@RequestParam String desc) {
        List<Espaco> espacosFiltrados = espacoRepository.findAll().stream()
                .filter(espaco -> espaco.getDescription().toLowerCase().contains(desc.toLowerCase()))
                .collect(Collectors.toList());

        return espacosFiltrados;
    }

    @GetMapping("/espaco/disponibility")
    public List<Espaco> getEspacoPorDisponibilty(@RequestParam String desc) {
        List<Espaco> espacosFiltrados = espacoRepository.findAll().stream()
                .filter(espaco -> espaco.getDisponibility().toLowerCase().contains(desc.toLowerCase()))
                .collect(Collectors.toList());

        return espacosFiltrados;
    }

    @GetMapping("/espaco/place")
    public List<Espaco> getEspacoPorPlace(@RequestParam String desc) {
        List<Espaco> espacosFiltrados = espacoRepository.findAll().stream()
                .filter(espaco -> espaco.getPlace().toLowerCase().contains(desc.toLowerCase()))
                .collect(Collectors.toList());

        return espacosFiltrados;
    }



    @PostMapping("/espaco")
    @ResponseStatus(HttpStatus.CREATED)
    public EspacoResponseDto criarPalco(@Valid @RequestBody EspacoRequestDto espacoRequestDto) {
      EspacoResponseDto espacoDto = espacoService.saveEspaco(espacoRequestDto);

        return espacoDto;

    }

    @DeleteMapping("/espaco/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEspaco(@PathVariable @Valid @NotNull Long id){
        espacoService.apagarEspacoPorId(id);
    }

    @PutMapping("/espaco/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EspacoResponseDto editarEspaco(@PathVariable Long id, @Valid @RequestBody EspacoRequestDto requestDto){

        EspacoResponseDto espacoResponseDto = espacoService.editarEspaco(id, requestDto);

        return espacoResponseDto;
    }

    @PatchMapping("/espaco/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarParcialmentePalco(@PathVariable Long id, @RequestBody EspacoRequestDto requestDto){

        espacoService.editarParcilamente(id, requestDto);

    }

}
