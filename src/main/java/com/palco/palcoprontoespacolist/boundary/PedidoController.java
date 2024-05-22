package com.palco.palcoprontoespacolist.boundary;

import com.palco.palcoprontoespacolist.control.PedidoService;
import com.palco.palcoprontoespacolist.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("palcopronto/pedidos")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obterTodosPedidos() {
        return pedidoService.obterTodosPedidos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        try {
            return pedidoService.criarPedido(pedido);
        } catch (Exception e) {
            logger.error("Erro ao criar pedido: ", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
        } catch (Exception e) {
            logger.error("Erro ao deletar pedido: ", e);
            throw e;
        }
    }
}
