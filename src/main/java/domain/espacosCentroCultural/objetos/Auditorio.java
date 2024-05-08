package domain.espacosCentroCultural.objetos;

public class Auditorio {

    private final String valor;

    private Auditorio(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static Auditorio criar(String id) {
        return new Auditorio(id);
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
