package crossover;

import base.Knapsack;

import java.util.ArrayList;

// HX
public class HeuristicCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        double ratio = 1.2;
        //offspring(child) = knapsack02 + ratio X (knapsack01 - knapsack02)
        return null;
    }
}