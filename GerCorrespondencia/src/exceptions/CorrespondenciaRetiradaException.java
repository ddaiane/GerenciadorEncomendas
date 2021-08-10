package exceptions;

public class CorrespondenciaRetiradaException extends Exception {

    public CorrespondenciaRetiradaException() {
        super("Essa correspondencia ja foi retirada.");
    }
}
