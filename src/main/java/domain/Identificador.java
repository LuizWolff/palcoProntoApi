package domain;

public abstract class Identificador <T> {

    public abstract T valor();

    @Override
    public String toString(){
        return this.valor().toString();
    }
}