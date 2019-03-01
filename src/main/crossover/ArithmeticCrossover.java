package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// AX
// Team 21
public class ArithmeticCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        ArrayList<Knapsack> knapsackChilds = new ArrayList<>();

        Knapsack betterFitness = knapsack02;
        Knapsack worseFitness = knapsack01;

        if (knapsack01.getTotal() > knapsack02.getTotal()) {
            betterFitness = knapsack01;
            worseFitness = knapsack02;
        }

        for (int j = 0; j < 2; j++) {
            ArrayList<Integer> tempChild = new ArrayList<>();
            while (tempChild.size() < 150) {
                // alpha [0,1]
                double alpha = random.nextDouble(true, true);
                // Bei diesem Index wird jeweils das Chromosom von beiden Knapsäcken in die neue Child Gene List geschrieben, bis die neue Liste voll ist (Größe 150)
                int index = ((int) (alpha * betterFitness.getTotal() + (1 - alpha) * worseFitness.getTotal())) % 150;
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