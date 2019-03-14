package crossover;

import base.Knapsack;
import crossover.Crossover;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// UNX
public class UniformCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {

        ArrayList<Knapsack> childs = new ArrayList<>();
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        childs.add(new Knapsack());
        childs.add(new Knapsack());
        MersenneTwisterFast rand = new MersenneTwisterFast();

        for(int i=0; i<knapsack01.getTotal(); i++)
        {
            if(rand.nextInt(2)==0)
            {
                c1.add(knapsack01.getGeneList().get(i));
                c2.add(knapsack02.getGeneList().get(i));
            }
            else
            {
                c1.add(knapsack02.getGeneList().get(i));
                c2.add(knapsack01.getGeneList().get(i));
            }
            childs.get(0).setGeneList(c1);
            childs.get(1).setGeneList(c2);
        }
        return childs;
    }
}