package domain.espacosCentroCultural.objetos;

public class AreaArLivre {

    private final String valor;

    private AreaArLivre(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static AreaArLivre criar(String id) {
        return new AreaArLivre(id);
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
