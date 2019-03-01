package base;

import random.MersenneTwisterFast;

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

    public void setGeneList(ArrayList<Integer> geneList) {
        this.geneList = geneList;
    }

    public int getTotal() {
        int total = 0;

        for (int i = 0; i < geneList.size(); i++)
            if (geneList.get(i) == 1) {
                //total += Configuration.instance.itemList.get(i).getValue();

            }
        MersenneTwisterFast random = new MersenneTwisterFast();
        //return total;
        return random.nextInt(0, 1133);
    }

    public String toString() {
        return geneList.toString();
    }
}