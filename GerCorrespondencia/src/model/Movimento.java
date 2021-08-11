package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Write a description of class model.Movimento here.
 *
 * @author (Caroline Scherer, Daiane Marcon, Mateus Vagner)
 * @version (1.0)
 */
public class Movimento
{
    private Correspondencia correspondencia; //na correspondencia tem o destinatário
    private String quemRegistra;
    private Calendar data;
    private String quemRetira; //Só para movimento de saída

    //model.Movimento de recebimento da correspondencia pela portaria
    public Movimento(Correspondencia correspondencia, String quemRegistra) {
            this.setCorrespondencia(correspondencia);
            this.setQuemRegistra(quemRegistra);
            setData(new GregorianCalendar());

    }

    //model.Movimento de entrega da correspondencia para o destinatário
    public Movimento(Correspondencia correspondencia, String quemRetira, String quemRegistra) {
        this.setCorrespondencia(correspondencia);
        correspondencia.setStatus(true);
            this.setQuemRetira(quemRetira);
            this.setQuemRegistra(quemRegistra);
            setData(new GregorianCalendar());

    }
    
    private String verData(){
        return (data.get(Calendar.DAY_OF_MONTH) +"/"+ (data.get(Calendar.MONTH)+1) + "/" + data.get(Calendar.YEAR));
    }

    public Calendar getData(){
        return this.data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getDataFormatada() {
        SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy");
        return df.format(data.getTime());
    }

    public Correspondencia getCorrespondencia() {
        return correspondencia;
    }

    public void setCorrespondencia(Correspondencia correspondencia) {
        this.correspondencia = correspondencia;
    }

    public String getQuemRegistra() {
        return quemRegistra;
    }

    public void setQuemRegistra(String quemRegistra) {
        this.quemRegistra = quemRegistra;
    }

    public String getQuemRetira() {
        return quemRetira;
    }

    public void setQuemRetira(String quemRetira) {
            this.quemRetira = quemRetira;
    }

    @Override
    public String toString() {
        String saida = "\nMovimento ";
        if(quemRetira != null) {saida += "de saída:";} else {saida += "de entrada:";}

        saida +="\nCorrespondencia " + correspondencia +
                "\nRegistrado por: " + quemRegistra;
        if (quemRetira != null) {saida += "\nRetirado por " + quemRetira + " na data " + verData();}
        else {saida += "\nRecebido no dia " + verData();}
        return saida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimento movimento = (Movimento) o;
        return correspondencia.equals(movimento.correspondencia) && getData().equals(movimento.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(correspondencia, getData());
    }


}
