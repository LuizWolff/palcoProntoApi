package domain.espacosCentroCultural.objetos;


import domain.Identificador;

public class EspacoId extends Identificador<String> {

    private final String valor;

    private EspacoId(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static EspacoId criar(String id) {
        return new EspacoId(id);
    }

    public String valor() {
        return this.valor;
    }

    private void validar() {
        if (this.valor == null || this.valor.length() != 7) {
            throw new IllegalArgumentException("Ingresso são 11 caracteres!");
        }
    }
}
