package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// IX
public class IntermediateCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        double ratio = random.nextDouble(true, true);
        //offspring(child) = knapsack01 + rand X ratio X (knapsack02-knapsack01)
        return null;
    }
}