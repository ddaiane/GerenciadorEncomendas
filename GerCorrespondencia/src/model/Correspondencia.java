package model;

import java.util.Objects;

/**
 * Write a description of class model.Correspondencia here.
 * 
 * @author (Caroline Scherer, Daiane Marcon, Mateus Vagner)
 * @version (1.0)
 */
public class Correspondencia //Generalização ou classe mãe
{
    private Destinatario destino;
    private boolean status; //TRUE é porque já foi retirado
    private int id;

    Id currentValue = Id.getInstance();

    public Correspondencia (Destinatario destino){
        this.setDestino(destino);
        this.id = currentValue.getNextValue(); //puxa o numero de id do contador da classe Id
        // por default status é inicializado com false.
    }    
     
    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean getStatus() { return status; }

    public long getId() {
        return id;
    }

    public Destinatario getDestino() {
        return destino;
    }

    public void setDestino(Destinatario destino) {
        this.destino = destino;
    }

    public String toString() {
        String saida =  "Destinatário: " + getDestino().toString();
        if (status) saida += "\nObjeto já foi retirado";
        else saida += "\nObjeto ainda não foi retirado";
        return saida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Correspondencia)) return false;
        Correspondencia that = (Correspondencia) o;
        return getStatus() == that.getStatus() && getDestino().equals(that.getDestino());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDestino(), getStatus());
    }
}
