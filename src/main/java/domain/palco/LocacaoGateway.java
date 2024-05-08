package domain.palco;

import java.util.List;

public interface LocacaoGateway {


        void adicionarNovaLocacao(Palco locacao);

        void atualizar(Palco locacao);

        Palco buscarPorId(String id);

        List<Palco> buscarTodos();
//        List<Palco> buscarData();

    }
