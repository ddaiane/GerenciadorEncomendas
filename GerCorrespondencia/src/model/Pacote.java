package model;

import java.util.Objects;

/**
 *
 * @author (Caroline Scherer, Daiane Marcon, Mateus Vagner)
 *
 */

public class Pacote extends Correspondencia
{
    private String empresa;


    /**
     * Construtor
     */
    public Pacote(Destinatario destino, String empresa)
    {
        super (destino);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }
   
    public String toString(){
        return super.toString() + "\nTipo: Pacote \nEmpresa remetente: "+ empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pacote pacote = (Pacote) o;
        return empresa.equals(pacote.empresa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), empresa);
    }
}
