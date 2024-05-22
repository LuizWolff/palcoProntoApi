package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.IngressoService;
import com.palco.palcoprontoespacolist.entity.Ingresso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("palcopronto/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @GetMapping
    public List<Ingresso> obterTodosIngressos() {
        return ingressoService.obterTodosIngressos();
    }

    @GetMapping("/{id}")
    public Optional<Ingresso> obterIngressoPorId(@PathVariable Long id) {
        return ingressoService.obterIngressoPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingresso criarIngresso(@RequestBody Ingresso ingresso) {
        return ingressoService.criarIngresso(ingresso);
    }

    @PutMapping("/{id}")
    public Ingresso atualizarIngresso(@PathVariable Long id, @RequestBody Ingresso ingressoAtualizado) {
        return ingressoService.atualizarIngresso(id, ingressoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarIngresso(@PathVariable Long id) {
        ingressoService.deletarIngresso(id);
    }
}
