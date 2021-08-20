package exceptions;


public class DestinatarioInexistenteException extends Exception {

    public DestinatarioInexistenteException() {
        super("Não há destinatario cadastrado com esses dados");
    }
}
