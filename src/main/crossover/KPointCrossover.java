package crossover;

import base.Knapsack;
import crossover.Crossover;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;

// KPX
public class KPointCrossover extends Crossover {
    public ArrayList<Knapsack> doCrossover(Knapsack knapsack01, Knapsack knapsack02) {
        int k = 3;
        ArrayList<Knapsack> childs = new ArrayList<>();
        ArrayList<Integer> c1 = new ArrayList<>();
        ArrayList<Integer> c2 = new ArrayList<>();
        childs.add(new Knapsack());
        childs.add(new Knapsack());
        MersenneTwisterFast rand = new MersenneTwisterFast();

        ArrayList<Integer> crosspoints = new ArrayList<>();
        for(int i = 0; i<k; i++)
        {
            int crosspoint;
            boolean equal=false;
            do{
                equal=false;
                crosspoint = rand.nextInt(knapsack01.getTotal());
                for(int j : crosspoints){
                    if(j==crosspoint)
                        equal=true;
                }
            }
            while (equal==true);
            crosspoints.add(crosspoint);
        }
        Collections.sort(crosspoints);
        int lastIndex = 0;
        for(int j = 0; j<k; j=j+2)
        {
            for (int i = lastIndex; i < crosspoints.get(j); i++) {
                c1.add(knapsack01.getGeneList().get(i));
                c2.add(knapsack02.getGeneList().get(i));
            }
            lastIndex=crosspoints.get(j);
            if(k-j-1 != 0)
            {
                for (int i = lastIndex; i < crosspoints.get(j+1); i++) {
                    c1.add(knapsack02.getGeneList().get(i));
                    c2.add(knapsack01.getGeneList().get(i));
                }
                lastIndex=crosspoints.get(j+1);
            }
            if(k-j-1 ==0)
            {
                for (int i = lastIndex; i < knapsack01.getTotal(); i++) {
                    c1.add(knapsack02.getGeneList().get(i));
                    c2.add(knapsack01.getGeneList().get(i));
                }
            }
            if(k-j-1 ==1)
            {
                for (int i = lastIndex; i < knapsack01.getTotal(); i++) {
                    c1.add(knapsack01.getGeneList().get(i));
                    c2.add(knapsack02.getGeneList().get(i));
                }
            }
        }
        childs.get(0).setGeneList(c1);
        childs.get(1).setGeneList(c2);

        return childs;
    }
}
