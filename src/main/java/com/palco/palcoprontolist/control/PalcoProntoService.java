package com.palco.palcoprontolist.control;

import com.palco.palcoprontolist.boundary.PalcoProntoRequestDto;
import com.palco.palcoprontolist.boundary.PalcoProntoResponseDto;
import com.palco.palcoprontolist.entity.PalcoPronto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalcoProntoService {
    private final PalcoProntoRepository palcoRepository;

    public PalcoProntoService(PalcoProntoRepository palcoRepository){
        this.palcoRepository = palcoRepository;
    }

    public PalcoProntoResponseDto savePalco(PalcoProntoRequestDto palcoDto){
        PalcoPronto palco = new PalcoPronto();

        palco.setName(palcoDto.getName());
        palco.setDescricao(palcoDto.getDescricao());
        palco.setPrice(palcoDto.getPrice());
        palco.setPlace(palcoDto.getPlace());
        palco.setStock(palcoDto.getStock());

       PalcoPronto savedPalco = palcoRepository.save(palco);

       PalcoProntoResponseDto responseDto = new PalcoProntoResponseDto(savedPalco.getId(),savedPalco.getName(),
               savedPalco.getDescricao(),savedPalco.getStock());

        return responseDto;
    }

    public List<PalcoProntoResponseDto> pegarPalco() {
        List<PalcoPronto> palco = palcoRepository.findAll();

        List<PalcoProntoResponseDto> palcoProntoResponseDtos  = palco.stream().map(palcoPronto ->
                new PalcoProntoResponseDto(palcoPronto.getId(),palcoPronto.getName(),
                        palcoPronto.getDescricao(), palcoPronto.getStock())).toList();

        return palcoProntoResponseDtos;
    }

    public void apagarPalcoPorId(Long id) {
        palcoRepository.deleteById(id);
    }

    public PalcoProntoResponseDto editarPalco(Long id, PalcoProntoRequestDto requestDto) {

        Optional<PalcoPronto> palcoEncontrado = palcoRepository.findById(id);

        if(palcoEncontrado.isPresent()){
            PalcoPronto palco = palcoEncontrado.get();

            palco.setName(requestDto.getName());
            palco.setDescricao(requestDto.getDescricao());
            palco.setPrice(requestDto.getPrice());
            palco.setStock(requestDto.getStock());
            palco.setPlace(requestDto.getPlace());

            PalcoPronto palcoEditado = palcoRepository.save(palco);

            return new PalcoProntoResponseDto(palcoEditado.getId(),palcoEditado.getName(),
                    palcoEditado.getDescricao(), palcoEditado.getStock());
        }

        return null;
    }

    public void editarParcilamente(Long id, PalcoProntoRequestDto requestDto) {
        Optional<PalcoPronto> palcoEncontrado = palcoRepository.findById(id);

        if(palcoEncontrado.isPresent()){
            PalcoPronto palcoPronto = palcoEncontrado.get();

            if(requestDto.getName() != null) {
                palcoPronto.setName(requestDto.getName());
            }

            if(requestDto.getDescricao() != null) {
                palcoPronto.setDescricao(requestDto.getDescricao());
            }

            if (requestDto.getPrice() != null ) {
                palcoPronto.setPrice(requestDto.getPrice());
            }

            if (requestDto.getStock() != null) {
                palcoPronto.setStock(requestDto.getStock());
            }

            if (requestDto.getPlace() != null) {
                palcoPronto.setPlace(requestDto.getPlace());
            }

            PalcoPronto palcoEditado = palcoRepository.save(palcoPronto);
        }
    }

}
