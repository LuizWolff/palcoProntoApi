package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.RelatorioService;
import com.palco.palcoprontoespacolist.entity.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("palcopronto/relatorios")
public class RelatorioController {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public List<Relatorio> obterTodosRelatorios() {
        return relatorioService.obterTodosRelatorios();
    }

    @GetMapping("/{id}")
    public Relatorio obterRelatorioPorId(@PathVariable Long id) {
        return relatorioService.obterRelatorioPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Relatorio criarRelatorio(@RequestBody RelatorioRequest relatorioRequest) {
        Long eventoId = relatorioRequest.getEventoId();
        Long espacoId = relatorioRequest.getEspacoId();

        return relatorioService.criarRelatorio(eventoId, espacoId);
    }

    @PutMapping("/{id}")
    public Relatorio atualizarRelatorio(@PathVariable Long id, @RequestBody Relatorio relatorioAtualizado) {
        return relatorioService.atualizarRelatorio(id, relatorioAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarRelatorio(@PathVariable Long id) {
        try {
            relatorioService.deletarRelatorio(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar relatório: ", e);
            throw e;
        }
    }

}
