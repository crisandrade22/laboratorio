package gov.sp.fatec.laboratorio.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Sacola {
    private static Sacola instance = new Sacola();

    private Map<Produto, Integer> sacola = new HashMap<>();

    private Sacola(){}


    public static Sacola getInstance() {
        return instance;
    }

    public void adicionar(Produto p, int quantidade) {
        sacola.put(p, quantidade);
    }

    public Set<Map.Entry<Produto, Integer>> entradas() {
        return sacola.entrySet();
    }

    public Set<Produto> chaves() {
        return sacola.keySet();
    }


    @Override
    public String toString() {
        return "Sacola{" +
                "sacola=" + sacola +
                '}';
    }
}
