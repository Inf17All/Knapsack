package base;

import configuration.Configuration;

import java.util.ArrayList;

public class Knapsack {
    private ArrayList<Integer> geneList = new ArrayList<>();

    public Knapsack() {
    }

    public Knapsack(Knapsack knapsack) {
        geneList.clear();
        geneList.addAll(knapsack.getGeneList());
    }

    public ArrayList<Integer> getGeneList() {
        return geneList;
    }
    
    // Setter für Crossover
    public void setGeneList(ArrayList<Integer> geneList) {
        this.geneList = geneList;
    }

    public int getTotal() {
        int total = 0;

        for (int i = 0; i < geneList.size(); i++)
            if (geneList.get(i) == 1)
                total += Configuration.instance.itemList.get(i).getValue();

        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Knapsack) {
            Knapsack compare = (Knapsack) obj;
            if (compare.getGeneList().equals(getGeneList())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return geneList.toString();
    }
}
