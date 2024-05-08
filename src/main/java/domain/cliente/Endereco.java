package domain.cliente;


import domain.cliente.objetos.CEP;

public class Endereco {

    private final CEP cep;
    private final String logradouro;
    private final String bairro;
    private final Cidade cidade;

    public Endereco(CEP cep, String logradouro, String bairro, Cidade cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        validar();
    }

    public static Endereco criar (CEP cep, String logradouro, String bairro, Cidade cidade){
        return new Endereco(cep, logradouro, bairro, cidade);
    }

    public void validar(){
        if(logradouro == null || logradouro.isBlank()){
            throw new IllegalArgumentException("Logradouro não pode ser vazio!");
        }
        if (bairro == null || bairro.isBlank()){
            throw new IllegalArgumentException("Bairro não pode ser vazio!");
        }
    }

    @Override
    public String toString(){
        return "Endereco{" + "cep=" + cep + ", logradouro=" + logradouro +
                ", bairro=" + bairro + ", cidade=" + cidade + "}";
    }


}
