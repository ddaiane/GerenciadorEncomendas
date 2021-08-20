package model;


/**
 * Classe padrão singleton geradora de id
 * @author (Daiane Marcon)
 *
 */
public class Id {
    private int currentValue = 1;
    private static Id instance = null;


    private Id() {
        int currentValue = 1;
    }

    static Id getInstance()
    {
        if (instance == null)
            instance = new Id();
        return instance;
    }

    public int getNextValue() {
        return currentValue++;
    } //função que retorna e autoincrementa os ids

}
