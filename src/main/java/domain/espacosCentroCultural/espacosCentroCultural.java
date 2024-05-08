package domain.espacosCentroCultural;


import domain.Identificador;
import domain.espacosCentroCultural.objetos.Ingresso;
import domain.palco.objetos.PalcoId;

public class espacosCentroCultural {

    private final Identificador id;
    private final Ingresso ingresso;
    private final PalcoId palcoId;
    private String cor;
    private final Integer ano;
    private boolean disponivel = true;

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    private espacosCentroCultural(Identificador id, Ingresso ingresso, PalcoId palcoId, String cor, int ano) {
        this.id = id;
        this.ingresso = ingresso;
        this.palcoId = palcoId;
        this.cor = cor;
        this.ano = ano;
        validar();
    }

    public static espacosCentroCultural criar(
            Identificador id,
            Ingresso ingresso,
            PalcoId palcoId,
            String cor,
            Integer ano
    ){
        return new espacosCentroCultural(id, ingresso, palcoId, cor, ano);
    }

    public void alterarCor (String novaCor){
        this.cor = novaCor;
    }

    public Identificador id() {
        return id;
    }

    public Ingresso tamanho() {
        return ingresso;
    }

    public PalcoId palcoId() {
        return palcoId;
    }

    public String cor() {
        return cor;
    }
    public Integer ano(){
        return Integer.valueOf(cor);
    }

    private void validar() {
        if (id == null) {
            throw new RuntimeException("ID nao pode ser nulo");
        }

        if (palcoId == null) {
            throw new RuntimeException("Modelo nao pode ser nulo");
        }

        if (cor == null || cor.trim().isEmpty()) {
            throw new RuntimeException("Cor nao pode ser nulo");
        }
        if (ano == null) {
            throw new RuntimeException("Ano nao pode ser nulo");
        }
    }

    @Override
    public String toString() {
        return "espacosCentroCultural{" +
                "id=" + id +
                ", palcoId=" + palcoId +
                ", cor='" + cor + '\'' +
                ", tamanho='" + ingresso + '\'' +
                ", ano=" + ano +
                '}';
    }
}
