package domain.palco;

import domain.Identificador;
import domain.cliente.Cliente;
import domain.espacosCentroCultural.espacosCentroCultural;
import domain.palco.objetos.StatusPalco;
import domain.palco.objetos.StatusPagamento;
import domain.espacosCentroCultural.objetos.StatusEspaco;

import java.time.LocalDate;

public class Palco {

    private final Identificador id;
    public StatusPalco statusPalco;
    public StatusEspaco statusEspaco;
    private StatusPagamento statusPagamento;
    private LocalDate dataInicioEvento;
    private LocalDate dataFimEvento;
    private Cliente cliente;
    private espacosCentroCultural palco;

    public espacosCentroCultural getPalco() {
        return palco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Palco(Identificador id, StatusPalco statusPalco, StatusEspaco
            statusVeiculo, StatusPagamento statusPagamento,
                 LocalDate dataInicioEvento, LocalDate dataFimEvento, Cliente cliente,
                 espacosCentroCultural palco) {
        this.id = id;
        this.statusPalco = statusPalco;
        this.statusEspaco = statusVeiculo;
        this.statusPagamento = statusPagamento;
        this.dataInicioEvento = dataInicioEvento;
        this.dataFimEvento = dataFimEvento;
        this.cliente = cliente;
        this.palco = palco;
    }

    public static Palco criar(
            Identificador id,
            StatusPalco statusPalco,
            StatusPagamento statusPagamento,
            StatusEspaco statusVeiculo,
            LocalDate dataRetirada,
            LocalDate dataDevolucao,
            Cliente cliente,
            espacosCentroCultural veiculo
    ){
        return new Palco(id, statusPalco,
                statusVeiculo, statusPagamento, dataRetirada,dataDevolucao, cliente, veiculo);
    }


    public void alterarStatusLocacao (StatusPalco alterarStatusLocacao){
        this.statusPalco = statusPalco;
    }

    public void alterarStatusPagamento (StatusPagamento alterarStatusPagamento){
        this.statusPagamento = statusPagamento;
    }

    public void alterarDataRetirada(LocalDate alterarDataRetirada){
        this.dataInicioEvento = dataInicioEvento;
    }





    public Palco(Identificador id, StatusPalco statusPalco, LocalDate dataRetirada, LocalDate dataDevolucao) {
        this.id = id;
        this.statusPalco = statusPalco;
        this.dataInicioEvento = dataRetirada;
        this.dataFimEvento = dataDevolucao;
    }

    public Identificador id() {
        return id;
    }

    public StatusPalco statusLocacao() {
        return statusPalco;
    }

    public void setStatusPalco(StatusPalco statusPalco){
        this.statusPalco = statusPalco;
    }

    public void alterarDataEvento(LocalDate alterarDataDevolucao){
        this.dataFimEvento = alterarDataDevolucao;
    }

    public StatusPagamento statusPagamento() {
        return statusPagamento;
    }

    private void validar() {
        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        if (statusPagamento == null) {
            throw new RuntimeException("Status Pagamento não pode ser nulo");
        }

        if (statusPalco == null){
            throw new RuntimeException("Status Locação não pode ser nulo");
        }
        if (dataInicioEvento == null) {
            throw new RuntimeException("Data Devolução não pode ser nulo");
        }
        if (dataFimEvento == null) {
            throw new RuntimeException("Data Devolução não pode ser nulo");
        }
    }

    @Override
    public String toString() {
        return "Palco{" +
                "id=" + id +
                ", Status pagamento=" + statusPagamento+
                ", Status statusPalco='" + statusPalco + '\'' +
                ", Data inicio evento='" + dataInicioEvento + '\'' +
                ", Data fim evento=" + dataFimEvento +
                '}';
    }

}
