package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// HX
public class HeuristicCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        // ratio fester Wert
        double ratio = 1.2;
        Knapsack betterFitness = knapsack02;
        Knapsack worseFitness = knapsack01;

        if(knapsack01.getTotal() > knapsack02.getTotal()) {
            betterFitness = knapsack01;
            worseFitness = knapsack02;
        }

        ArrayList<Knapsack> knapsackChilds = new ArrayList<>();

        for (int j = 0; j < 2; j++) {
            ArrayList<Integer> tempChild = new ArrayList<>();
            while (tempChild.size() < 150) {
                // Bei diesem Index wird jeweils das Chromosom von beiden Knapsäcken in die neue Child Gene List geschrieben, bis die neue Liste voll ist (Größe 150)
                // Index kann nur zwischen 0 und 150 liegen, immer positiver Wert
                int index = Math.abs((int) (betterFitness.getTotal() + ratio * (worseFitness.getTotal() - betterFitness.getTotal())) % 150);
                tempChild.add(betterFitness.getGeneList().get(index));
                tempChild.add(worseFitness.getGeneList().get(index));
            }
            Knapsack tempKnapsackChild = new Knapsack();
            tempKnapsackChild.setGeneList(tempChild);
            knapsackChilds.add(tempKnapsackChild);
        }
        return knapsackChilds;
    }
}