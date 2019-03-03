package selection;

import base.Knapsack;
import base.Population;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// RS
public class RankSelection extends Selection {

    public ArrayList<Knapsack> doSelection(Population population) {

        MersenneTwisterFast mtf = new MersenneTwisterFast(System.nanoTime());
        ArrayList<Knapsack> knapsackList = population.getKnapsackList(); // unsorted knapsacks

        knapsackList.sort(Comparator.comparingInt(Knapsack::getTotal).reversed()); // sort knapsacks (ascending fitness)

        int totalRanks = 0; // sum of the ranks to calculate probabilities

        for (int i = 0; i < knapsackList.size(); i++) totalRanks += i + 1; // add up ranks

        // map knapsacks and their probabilities
        Map<Knapsack, Double> probabilityMap = new HashMap<Knapsack, Double>();
        for (int i = 0; i < knapsackList.size(); i++) probabilityMap.put(knapsackList.get(i), ((i + 1) * 1.0) / totalRanks);

        // select individuals to remove from population
        int populationSizeBeforeSelection = knapsackList.size();
        int i = 0; // index
        ArrayList<Knapsack> sortedAndUnchangedKnapsackList = knapsackList; // sorted knapsacks (won't change)

        while(knapsackList.size() > populationSizeBeforeSelection * 0.8){ // keep 80 % of the population alive

            if(!mtf.nextBoolean(probabilityMap.get(sortedAndUnchangedKnapsackList.get(i)))) knapsackList.remove(i);

            i = (i + 1) % knapsackList.size();
        }

        return knapsackList;
    }
}