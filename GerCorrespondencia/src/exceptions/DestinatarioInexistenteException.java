package exceptions;

public class DestinatarioInexistenteException extends Exception {

    public DestinatarioInexistenteException(String numero) {
        super("Nao ha desinatario cadastrado no apartamento " + numero + ".");
    }
}
