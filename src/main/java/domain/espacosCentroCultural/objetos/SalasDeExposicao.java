package domain.espacosCentroCultural.objetos;

public class SalasDeExposicao {

    private final String valor;

    private SalasDeExposicao(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static SalasDeExposicao criar(String id) {
        return new SalasDeExposicao(id);
    }

    public String valor() {
        return this.valor;
    }

    private void validar() {
        if (this.valor == null || this.valor.length() != 11) {
            throw new IllegalArgumentException("Ingresso são 11 caracteres!");
        }
    }
}
