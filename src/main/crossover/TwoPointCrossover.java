package main.crossover;

import base.Knapsack;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.List;

// 2PX
public class TwoPointCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {

        ArrayList<Knapsack> childs = new ArrayList<>();
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        int crosspoint1;
        int crosspoint2;
        childs.add(new Knapsack());
        childs.add(new Knapsack());
        MersenneTwisterFast rand = new MersenneTwisterFast();
        boolean equal;
        do {
            crosspoint1 = rand.nextInt(knapsack01.getTotal());
            crosspoint2 = rand.nextInt(knapsack01.getTotal());
            if(crosspoint1 == crosspoint2)
                equal=true;
            else
                equal=false;
        }
        while (equal==true);
        if (crosspoint2 < crosspoint1) {
            int temp = crosspoint1;
            crosspoint1 = crosspoint2;
            crosspoint2 = temp;
        }
        int lastIndex = 0;
        for (int i = lastIndex; i < crosspoint1; i++) {
            c1.add(knapsack01.getGeneList().get(i));
            c2.add(knapsack02.getGeneList().get(i));
        }
        lastIndex = crosspoint1;
        for (int i = lastIndex; i < crosspoint2; i++) {
            c1.add(knapsack02.getGeneList().get(i));
            c2.add(knapsack01.getGeneList().get(i));
        }
        lastIndex = crosspoint2;
        for (int i = lastIndex; i < knapsack01.getTotal(); i++) {
            c1.add(knapsack01.getGeneList().get(i));
            c2.add(knapsack02.getGeneList().get(i));
        }
        childs.get(0).setGeneList(c1);
        childs.get(1).setGeneList(c2);

        return childs;
    }
}