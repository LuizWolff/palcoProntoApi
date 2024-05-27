package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Ingresso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    // Obter todos os ingressos
    public List<Ingresso> obterTodosIngressos() {
        return ingressoRepository.findAll();
    }

    // Obter ingresso por ID
    public Optional<Ingresso> obterIngressoPorId(Long id) {
        return ingressoRepository.findById(id);
    }

    // Criar novo ingresso
    public Ingresso criarIngresso(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    // Atualizar ingresso existente
    public Ingresso atualizarIngresso(Long id, Ingresso ingressoAtualizado) {
        return ingressoRepository.findById(id)
                .map(ingresso -> {
                    ingresso.setTipo(ingressoAtualizado.getTipo());
                    ingresso.setPreco(ingressoAtualizado.getPreco());
                    ingresso.setQuantidadeDisponivel(ingressoAtualizado.getQuantidadeDisponivel());
                    ingresso.setQuantidadeComprado(ingressoAtualizado.getQuantidadeComprado());
                    return ingressoRepository.save(ingresso);
                })
                .orElseGet(() -> {
                    ingressoAtualizado.setId(id);
                    return ingressoRepository.save(ingressoAtualizado);
                });
    }

    // Deletar ingresso
    public void deletarIngresso(Long id) {
        ingressoRepository.deleteById(id);
    }
}