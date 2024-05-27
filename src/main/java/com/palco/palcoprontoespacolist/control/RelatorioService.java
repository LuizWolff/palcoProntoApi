package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Espaco;
import com.palco.palcoprontoespacolist.entity.Eventos;
import com.palco.palcoprontoespacolist.entity.Ingresso;
import com.palco.palcoprontoespacolist.entity.Relatorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Autowired
    private EventosRepository eventosRepository;

    @Autowired
    private EspacoRepository espacoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Relatorio> obterTodosRelatorios() {
        return relatorioRepository.findAll();
    }

    public Relatorio obterRelatorioPorId(Long id) {
        return relatorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado"));
    }

    public Relatorio criarRelatorio(Long eventoId, Long espacoId) {
        Optional<Eventos> optionalEvento = eventosRepository.findById(eventoId);
        Optional<Espaco> optionalEspaco = espacoRepository.findById(espacoId);


        if (optionalEvento.isPresent() && optionalEspaco.isPresent()) {
            Eventos evento = optionalEvento.get();
            Espaco espaco = optionalEspaco.get();

            Relatorio relatorio = new Relatorio();
            relatorio.setEventos(evento);
            relatorio.setEspaco(espaco);

            // Obtenha a lista de ingressos associados ao evento
            List<Ingresso> ingressos = evento.getIngressos();


            // Calcule o precoTotal como a soma dos preços de todos os ingressos
            BigDecimal precoTotal = BigDecimal.ZERO;

            for (Ingresso ingresso : ingressos) {
                BigDecimal precoIngresso = BigDecimal.valueOf(ingresso.getPreco());
                BigDecimal quantidadeComprada = BigDecimal.valueOf(ingresso.getQuantidadeComprado());
                BigDecimal precoTotalIngresso = precoIngresso.multiply(quantidadeComprada);
                precoTotal = precoTotal.add(precoTotalIngresso);
            }

            relatorio.setPrecoTotalVendidoIngresso(precoTotal);


            // Configure o valor de disponibility
            relatorio.setDisponibilityEspaco(espaco.getDisponibility());

            // Salve o relatório no banco de dados
            return relatorioRepository.save(relatorio);
        } else {
            throw new EntityNotFoundException("Evento ou espaço não encontrado");
        }
    }

    private String obterDisponibilidadeEspaco(Espaco espaco) {
        return espaco.getDisponibility();
    }

    public Relatorio atualizarRelatorio(Long id, Relatorio relatorioAtualizado) {
        return relatorioRepository.findById(id)
                .map(relatorio -> {
                    relatorio.setEventos(relatorioAtualizado.getEventos());
                    relatorio.setEspaco(relatorioAtualizado.getEspaco());
                    return relatorioRepository.save(relatorio);
                })
                .orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado"));
    }

    public void deletarRelatorio(Long id) {
        relatorioRepository.deleteById(id);
    }
}
