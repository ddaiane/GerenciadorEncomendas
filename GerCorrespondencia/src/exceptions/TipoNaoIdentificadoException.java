package exceptions;

public class TipoNaoIdentificadoException extends Exception {

    public TipoNaoIdentificadoException() {
        super("Tipo nao identificado. Digite um tipo valido");
    }

}
