package crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.lang.reflect.Array;
import java.util.ArrayList;

// SX
public class ScatteredCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        MersenneTwisterFast random = new MersenneTwisterFast();
        //create random bytes -> 150 Items
        ArrayList<Integer> randomBytes = new ArrayList<>();
        for (int i = 0; i < 150; i++) randomBytes.add(random.nextInt(0, 1));

        //Wenn 1 dann Gen von knapsack1, wenn 0 dann Gen von knapsack2
        return null;
    }
}