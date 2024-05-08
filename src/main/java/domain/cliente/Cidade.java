package domain.cliente;

public class Cidade {

    private final String nome;
    private final String uf;

    public Cidade(String nome, String uf) {
        this.nome = nome;
        this.uf = uf;
    }

    public static Cidade criar(String nome, String uf){
    return new Cidade(nome, uf);
    }

    public String nome() {
        return nome;
    }

    public String uf(){
    return this.uf;
    }

    private void validar(){
    if (nome == null || nome.isBlank()){
        throw new IllegalArgumentException("Nome não pode ser vazio");
    }
    if (uf == null || uf.isBlank()){
        throw new IllegalArgumentException("UF não pode ser vazio!");
    }
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
