package model;

//classe pra ir gerando os numeros para a id de cada correspondencia
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
    }

}
