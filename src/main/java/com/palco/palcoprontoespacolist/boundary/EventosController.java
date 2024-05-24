package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.EventosService;
import com.palco.palcoprontoespacolist.entity.Eventos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("palcopronto/eventos")
public class EventosController {

    @Autowired
    private EventosService eventosService;

    @GetMapping
    public List<Eventos> obterTodosEventos() {
        return eventosService.obterTodosEventos();
    }

    @GetMapping("/{id}")
    public Optional<Eventos> obterIngressoPorId(@PathVariable Long id) {
        return eventosService.obterEventosPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Eventos criarEventoComEspacoEIngressos(@RequestBody CriarEventoRequest request) {
        return eventosService.criarEventoComEspacoEIngressos(request.getName(), request.getDescription(),request.getDateTime(), request.getIdEspaco(), request.getIdsIngressos());
    }

    @PutMapping("/{id}")
    public Eventos atualizarEvento(
            @PathVariable Long id,
            @RequestBody AtualizarEventoRequest request) {

        return eventosService.atualizarEventos(
                id,
                request.getName(),
                request.getDescription(),
                request.getDateTime(),
                request.getIdEspaco(),
                request.getIdsIngressos()
        );
    }

    @DeleteMapping("/{id}")
    public void deletarEventos(@PathVariable Long id) {
        eventosService.deletarEventos(id);
    }
}