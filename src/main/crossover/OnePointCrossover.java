package main.crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// 1PX
public class OnePointCrossover extends Crossover {

    MersenneTwisterFast rand = new MersenneTwisterFast();
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {

        ArrayList<Knapsack> childs = new ArrayList<>();
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        childs.add(new Knapsack());
        childs.add(new Knapsack());

        int crosspoint = rand.nextInt(knapsack01.getTotal());

        for (int i = 0; i < crosspoint; i++) {
            c1.add(knapsack01.getGeneList().get(i));
            c2.add(knapsack02.getGeneList().get(i));
        }
        for (int i = crosspoint; i < knapsack01.getTotal(); i++) {
            c1.add(knapsack02.getGeneList().get(i));
            c2.add(knapsack01.getGeneList().get(i));
        }

        childs.get(0).setGeneList(c1);
        childs.get(1).setGeneList(c2);

        return childs;
    }


}