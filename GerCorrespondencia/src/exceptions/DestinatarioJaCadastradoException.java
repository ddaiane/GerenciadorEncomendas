package exceptions;

public class DestinatarioJaCadastradoException extends Exception {

    public DestinatarioJaCadastradoException() {
        super("Esse destinatario ja esta cadastrado.");
    }
}
