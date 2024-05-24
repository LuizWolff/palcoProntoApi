package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.IngressoService;
import com.palco.palcoprontoespacolist.entity.Ingresso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("palcopronto/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping
    public List<IngressoResponse> obterTodosIngressos() {
        List<Ingresso> ingressos = ingressoService.obterTodosIngressos();
        return ingressos.stream()
                .map(this::mapToIngressoResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<IngressoResponse> obterIngressoPorId(@PathVariable Long id) {
        Optional<Ingresso> ingresso = ingressoService.obterIngressoPorId(id);
        return ingresso.map(this::mapToIngressoResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngressoResponse criarIngresso(@RequestBody Ingresso ingresso) {
        Ingresso novoIngresso = ingressoService.criarIngresso(ingresso);
        return mapToIngressoResponse(novoIngresso);
    }

    @PutMapping("/{id}")
    public IngressoResponse atualizarIngresso(@PathVariable Long id, @RequestBody Ingresso ingressoAtualizado) {
        Ingresso ingresso = ingressoService.atualizarIngresso(id, ingressoAtualizado);
        return mapToIngressoResponse(ingresso);
    }

    @DeleteMapping("/{id}")
    public void deletarIngresso(@PathVariable Long id) {
        ingressoService.deletarIngresso(id);
    }

    private IngressoResponse mapToIngressoResponse(Ingresso ingresso) {
        return new IngressoResponse(ingresso.getId(), ingresso.getTipo(), ingresso.getPreco(), ingresso.getQuantidadeDisponivel());
    }
}
