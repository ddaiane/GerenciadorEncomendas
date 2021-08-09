package exceptions;

public class DestinatarioInexistenteException extends Exception {

    public DestinatarioInexistenteException(String nome) {
        super("Nao ha destinatario cadastrado com o nome " + nome + ".");
    }
}
