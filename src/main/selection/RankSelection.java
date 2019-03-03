package selection;

import base.Knapsack;
import base.Population;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// RS
public class RankSelection extends Selection {

    public ArrayList<Knapsack> doSelection(Population population) {

        MersenneTwisterFast mtf = new MersenneTwisterFast(System.nanoTime());
        ArrayList<Knapsack> knapsackList = population.getKnapsackList(); // unsorted knapsacks

        knapsackList.sort(Comparator.comparingInt(Knapsack::getTotal).reversed()); // sort knapsacks (ascending fitness)

        int totalRanks = 0; // sum of the ranks to calculate probabilities

        for (int i = 0; i < knapsackList.size(); i++) totalRanks += i + 1; // add up ranks

        for (Knapsack k : knapsackList) { // todo: remove optimal amout of elements!

            int knapsackIndex = knapsackList.indexOf(k);

            if(!mtf.nextBoolean((knapsackIndex * 1.0) / totalRanks)) knapsackList.remove(k);
        }

        return knapsackList;
    }
}