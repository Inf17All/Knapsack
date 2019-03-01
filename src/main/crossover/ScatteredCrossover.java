package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// SX
// Team 21
// Erstellen einer random Zahl 0 oder 1
// Wenn die random Zahl 1 ist, wird das Chromosom von Knapsack 1 genommen, bei 0 das Chromosom von Knapsack 0
public class ScatteredCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        ArrayList<Knapsack> knapsackChilds = new ArrayList<>();
        MersenneTwisterFast random = new MersenneTwisterFast();

        for (int j = 0; j < 2; j++) {
            ArrayList<Integer> tempChild = new ArrayList<>();
            for (int i = 0; i < 150; i++) {
                if (random.nextInt(0, 1) == 1) tempChild.add(knapsack01.getGeneList().get(i));
                else tempChild.add(knapsack02.getGeneList().get(i));
            }
            Knapsack tempKnapsackChild = new Knapsack();
            tempKnapsackChild.setGeneList(tempChild);
            knapsackChilds.add(tempKnapsackChild);
        }

        return knapsackChilds;
    }
}