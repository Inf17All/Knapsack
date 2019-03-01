package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// IX
// Team 21
// Berechnung Index: ratio*rand
// Bei diesem Index wird jeweils das Chromosom von beiden Knapsäcken in die neue Child Gene List geschrieben, bis die neue Liste voll ist (Größe 150)
public class IntermediateCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        ArrayList<Knapsack> knapsackChilds = new ArrayList<>();
        MersenneTwisterFast random = new MersenneTwisterFast();

        // 2 neue Childs
        for (int j = 0; j < 2; j++) {
            ArrayList<Integer> tempChild = new ArrayList<>();
            while (tempChild.size() < 150) {
                // ratio [0,1]
                double ratio = random.nextDouble(true, true);
                // rand 0-149
                double rand = random.nextInt(150);
                int index = (int) (ratio * rand);
                tempChild.add(knapsack01.getGeneList().get(index));
                tempChild.add(knapsack02.getGeneList().get(index));
            }
            Knapsack tempKnapsackChild = new Knapsack();
            tempKnapsackChild.setGeneList(tempChild);
            knapsackChilds.add(tempKnapsackChild);
        }
        return knapsackChilds;
    }
}