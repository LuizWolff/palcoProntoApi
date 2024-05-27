package com.palco.palcoprontoespacolist.control;

import com.palco.palcoprontoespacolist.entity.Ingresso;
import com.palco.palcoprontoespacolist.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private IngressoRepository ingressoRepository;

    // Obter todos os pedidos
    public List<Pedido> obterTodosPedidos() {
        return pedidoRepository.findAll();
    }

    // Criar novo pedido
    public Pedido criarPedido(Pedido pedido) {
        try {
            logger.debug("Tentando criar pedido: {}", pedido);
            Ingresso ingresso = ingressoRepository.findById(pedido.getIngresso().getId())
                    .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));
            logger.debug("Ingresso encontrado: {}", ingresso);
            if (ingresso.getQuantidadeDisponivel() >= pedido.getQuantidade()) {
                ingresso.setQuantidadeDisponivel(ingresso.getQuantidadeDisponivel() - pedido.getQuantidade());
                ingresso.setQuantidadeComprado(ingresso.getQuantidadeComprado() + pedido.getQuantidade());
                ingressoRepository.save(ingresso);
                Pedido pedidoSalvo = pedidoRepository.save(pedido);
                logger.debug("Pedido criado com sucesso: {}", pedidoSalvo);
                return pedidoSalvo;
            } else {
                throw new RuntimeException("Quantidade de ingressos insuficiente");
            }
        } catch (Exception e) {
            logger.error("Erro ao criar pedido: ", e);
            throw e;
        }
    }

    // Deletar pedido
    public void deletarPedido(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar pedido: ", e);
            throw e;
        }
    }
}