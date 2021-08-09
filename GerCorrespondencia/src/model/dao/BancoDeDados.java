package model.dao;

import model.Correspondencia;
import model.Destinatario;
import model.Movimento;

import java.util.ArrayList;

class BancoDeDados {
    private final ArrayList<Correspondencia> correspondencias;
    private final ArrayList<Destinatario> destinatarios;
    private final ArrayList<Movimento> movimentos;

    private static BancoDeDados instance = null;

    // private constructor restricted to this class itself
    private BancoDeDados()
    {
        correspondencias = new ArrayList<>();
        destinatarios = new ArrayList<>();
        movimentos = new ArrayList<>();
    }

    static BancoDeDados getInstance()
    {
        if (instance == null)
            instance = new BancoDeDados();
        return instance;
    }

    ArrayList<Destinatario> getDestinatarios(){
        return this.destinatarios;
    }

    public ArrayList<Correspondencia> getCorrespondencias() {
        return correspondencias;
    }

    public ArrayList<Movimento> getMovimentos() {
        return movimentos;
    }


}
