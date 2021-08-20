package model;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author (Caroline Scherer, Daiane Marcon, Mateus Vagner)
 *
 */

public class Destinatario
{
    private String nome; 
    private String numeroImovel;

    public Destinatario(String nome, String numeroImovel)
    {
            this.nome = nome;
            this.numeroImovel = numeroImovel;
    }
    
    public String getNome(){
        return nome;
    }
        
    public String getNumeroImovel(){
        return numeroImovel;
    }
    
    public String toString(){
        return (nome + "\nNumero do Imóvel: "+ numeroImovel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinatario that = (Destinatario) o;
        return this.nome.equals(that.nome) && numeroImovel.equals(that.numeroImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numeroImovel);
    }

}
