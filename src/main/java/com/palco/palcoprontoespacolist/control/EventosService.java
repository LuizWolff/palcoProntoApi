package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Espaco;
import com.palco.palcoprontoespacolist.entity.Eventos;
import com.palco.palcoprontoespacolist.entity.Ingresso;
import com.palco.palcoprontoespacolist.boundary.IngressoItem;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EventosService {

    @Autowired
    private EventosRepository eventosRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    public List<Eventos> obterTodosEventos() {
        return eventosRepository.findAll();
    }

    public Optional<Eventos> obterEventosPorId(Long id) {
        return eventosRepository.findById(id);
    }

    public Eventos criarEventoComEspacoEIngressos(String name, String description, LocalDateTime dateTime, Long idEspaco, List<Long> idsIngressos) {

        // Obter o espaço pelo ID
        Espaco espaco = espacoRepository.findById(idEspaco)
                .orElseThrow(() -> new IllegalArgumentException("Espaço não encontrado"));

        // Obter os ingressos pelos IDs
        List<Ingresso> ingressos = ingressoRepository.findAllById(idsIngressos);

        // Criar o evento
        Eventos evento = new Eventos();
        evento.setName(name);
        evento.setDescription(description);
        evento.setDatetime(dateTime);
        evento.setEspaco(espaco);
        evento.setIngressos(ingressos);

        // Salvar o evento no banco de dados
        return eventosRepository.save(evento);
    }



    public Eventos atualizarEventos(Long id, Eventos eventosAtualizado) {
        return eventosRepository.findById(id)
                .map(eventos -> {
                    eventos.setName(eventosAtualizado.getName());
                    eventos.setDescription(eventosAtualizado.getDescription());
                    eventos.setEspaco(eventosAtualizado.getEspaco());
                    eventos.setIngressos(eventosAtualizado.getIngressos());
                    return eventosRepository.save(eventos);
                })
                .orElseGet(() -> {
                    eventosAtualizado.setId(id);
                    return eventosRepository.save(eventosAtualizado);
                });
    }
    public void deletarEventos(Long id) {
        eventosRepository.deleteById(id);
    }
}