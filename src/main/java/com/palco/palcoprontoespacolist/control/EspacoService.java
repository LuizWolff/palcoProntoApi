package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.boundary.EspacoRequestDto;
import com.palco.palcoprontoespacolist.boundary.EspacoResponseDto;
import com.palco.palcoprontoespacolist.entity.Espaco;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {
    private final EspacoRepository espacoRepository;
//    private final EventoService eventoService;

    public EspacoService(EspacoRepository palcoRepository){
        this.espacoRepository = palcoRepository;
    }

    public EspacoResponseDto saveEspaco(EspacoRequestDto espacoDto){
        Espaco espaco = new Espaco();

        espaco.setName(espacoDto.getName());
        espaco.setDescription(espacoDto.getDescription());
        espaco.setPlace(espacoDto.getPlace());
        espaco.setPeopleCapacity(espacoDto.getPeopleCapacity());
        espaco.setDisponibility(espacoDto.getDisponibility());

       Espaco savedEspaco = espacoRepository.save(espaco);

       EspacoResponseDto responseDto = new EspacoResponseDto(savedEspaco.getId(),savedEspaco.getName(),
               savedEspaco.getDescription(),savedEspaco.getPlace(),savedEspaco.getPeopleCapacity(),
               savedEspaco.getDisponibility());

        return responseDto;
    }

    public List<EspacoResponseDto> getEspaco() {
        List<Espaco> getEspaco = espacoRepository.findAll();

        List<EspacoResponseDto> espacoResponseDtos  = getEspaco.stream().map(espaco ->
                new EspacoResponseDto(espaco.getId(),espaco.getName(),
                        espaco.getDescription(),espaco.getPlace(), espaco.getPeopleCapacity(), espaco.getDisponibility())).toList();

        return espacoResponseDtos;
    }

    public void apagarEspacoPorId(Long id) {
        espacoRepository.deleteById(id);
    }

    public EspacoResponseDto editarEspaco(Long id, EspacoRequestDto requestDto) {

        Optional<Espaco> palcoEncontrado = espacoRepository.findById(id);

        if(palcoEncontrado.isPresent()){
            Espaco espaco = palcoEncontrado.get();

            espaco.setName(requestDto.getName());
            espaco.setDescription(requestDto.getDescription());
            espaco.setPeopleCapacity(requestDto.getPeopleCapacity());
            espaco.setPlace(requestDto.getPlace());
            espaco.setDisponibility(requestDto.getDisponibility());

            Espaco espacoEditado = espacoRepository.save(espaco);

            return new EspacoResponseDto(espacoEditado.getId(),espacoEditado.getName(),
                    espacoEditado.getDescription(),espacoEditado.getPlace(), espacoEditado.getPeopleCapacity(),
                    espacoEditado.getDisponibility());
        }

        return null;
    }

    public void editarParcilamente(Long id, EspacoRequestDto requestDto) {
        Optional<Espaco> palcoEncontrado = espacoRepository.findById(id);

        if(palcoEncontrado.isPresent()){
            Espaco palcoPronto = palcoEncontrado.get();

            if(requestDto.getName() != null) {
                palcoPronto.setName(requestDto.getName());
            }

            if(requestDto.getDescription() != null) {
                palcoPronto.setDescription(requestDto.getDescription());
            }

            if (requestDto.getPeopleCapacity() != null) {
                palcoPronto.setPeopleCapacity(requestDto.getPeopleCapacity());
            }

            if (requestDto.getPlace() != null) {
                palcoPronto.setPlace(requestDto.getPlace());
            }
            if (requestDto.getDisponibility() != null ) {
                palcoPronto.setDisponibility(requestDto.getDisponibility());
            }

            Espaco espacoEditado = espacoRepository.save(palcoPronto);
        }
    }

    //Quando for criar um evento, vai ser necessário criar um metodo de espaco existente
    // e criar um evento em cima desse espaco

//    public EspacoService(EspacoRepository espacoRepository, EventoService eventoService){
//        this.espacoRepository = espacoRepository;
//        this.eventoService = eventoService;
//    }
//
//    // Métodos existentes do EspacoService
//

//    public void criarEventoParaEspaco(Long espacoId, String nomeEvento, Date dataEvento) {
//        Espaco espaco = espacoRepository.findById(espacoId).orElseThrow(() -> new RuntimeException("Espaço não encontrado"));
//
//        if (Disponibility.DISPONIVEL.name().equals(espaco.getDisponibility())) {
//            eventoService.criarEvento(espaco, nomeEvento, dataEvento);
//        } else {
//            throw new RuntimeException("Espaço não está disponível para criar um evento");
//        }
//    }


    // aqui é um endpoint criado para executar a criação de um evento no PostMan

//    @RestController
//    @RequestMapping("/espacos")
//    public class EspacoController {
//
//        @Autowired
//        private EspacoService espacoService;
//
//        @PostMapping("/{espacoId}/eventos")
//        public ResponseEntity<String> criarEventoParaEspaco(@PathVariable Long espacoId, @RequestParam String nomeEvento, @RequestParam String dataEvento) {
//            // Converta a dataEvento para o formato desejado, se necessário
//            Date data = // Converta a string dataEvento para Date
//
//                    espacoService.criarEventoParaEspaco(espacoId, nomeEvento, data);
//
//            return ResponseEntity.ok("Evento criado com sucesso para o espaço com ID: " + espacoId);
//        }
//    }

}
