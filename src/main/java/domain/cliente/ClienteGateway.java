package domain.cliente;

import java.util.List;

public interface ClienteGateway {

    void registrar(Cliente cliente);

    void atualizar(Cliente cliente);

    Cliente buscarPorId(String id);

    List<Cliente> buscarTodos();


}
