package model;

import java.util.Objects;

/**
 * Write a description of class model.Carta here.
 * 
 *
 * @author (Caroline Scherer, Daiane Marcon, Mateus Vagner)
 * @version (1.0)
 */
public class Carta extends Correspondencia
{
    private boolean recibo; //Se TRUE é porque tinha recibo

    /**
     * Constructor for objects of class model.Carta
     */
    public Carta(Destinatario destino, boolean recibo)
    {
       super(destino);
       this.recibo = recibo;
    }

    public boolean getRecibo(){
        return recibo;
    }
    
    public String toString(){
        String saida = super.toString();
        if (recibo) saida += "\nCarta com recibo";
        else saida += "\nCarta sem recibo";
        return saida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Carta carta = (Carta) o;
        return recibo == carta.recibo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), recibo);
    }
}
