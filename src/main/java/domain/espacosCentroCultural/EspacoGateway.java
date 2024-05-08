package domain.espacosCentroCultural;

import java.util.List;

public interface EspacoGateway {

    void registrar(espacosCentroCultural veiculo);

    void atualizar(espacosCentroCultural veiculo);

    espacosCentroCultural buscarPorId(String id);

    List<espacosCentroCultural> buscarTodos();

}
