package domain.cliente.objetos;


public class CEP {
    private final String valor;

    public CEP(String valor) {
        this.valor = valor;
        this.validar();
    }

    public static CEP criar(String valor) {
    return new CEP(valor);
    }

    private void validar(){
    if (valor == null || valor.isBlank() || valor.length() !=8 || valor.charAt(0) == '-'){
        throw new IllegalArgumentException("CEP inválido!");
    }
    try {
        Integer.valueOf(valor);
    } catch (NumberFormatException e){
        throw new IllegalArgumentException("CEP inválido");
        }
    }

    public String valor(){
        return valor;
    }

    @Override
    public String toString() {
        return "CEP{" +
                "valor='" + valor + '\'' +
                '}';
    }
}
