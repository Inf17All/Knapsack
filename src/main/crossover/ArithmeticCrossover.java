package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// AX
public class ArithmeticCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        double alpha = random.nextDouble(true, true);
        ArrayList<Integer> geneList01 = knapsack01.getGeneList();
        ArrayList<Integer> geneList02 = knapsack02.getGeneList();
        //offspring = alpha X knapsack01 + (1-alpha) X knapsack02
        return null;
    }
}