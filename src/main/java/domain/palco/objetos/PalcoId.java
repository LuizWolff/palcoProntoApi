package domain.palco.objetos;


import domain.Identificador;

public class PalcoId extends Identificador<String> {

    private final String valor;

    private PalcoId(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static PalcoId criar(String id) {
        return new PalcoId(id);
    }

    public String valor() {
        return this.valor;
    }

    private void validar() {
        if (this.valor == null) {
            throw new IllegalArgumentException("PalcoId não pode ser nulo!");
        }
    }
}
