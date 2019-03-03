package selection;

import base.Knapsack;
import base.Population;
import random.MersenneTwisterFast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// BS
public class BoltzmannSelection extends Selection {

    private final double kB = 1.38064852e-23; // joule per kelvin

    public ArrayList<Knapsack> doSelection(Population population) {

        ArrayList<Knapsack> knapsacks = population.getKnapsackList();

        MersenneTwisterFast mtf = new MersenneTwisterFast(System.nanoTime());

        // formula: p(e) = exp(-e/(kB*T))

        // T: average fitness of population
        double temperature = knapsacks.stream().mapToInt(Knapsack::getTotal).average().getAsDouble();

        // map knapsacks and their probabilities
        Map<Knapsack, Double> probabilityMap = new HashMap<Knapsack, Double>();
        for (int i = 0; i < knapsacks.size(); i++) {

            Knapsack currentKnapsack = knapsacks.get(i);

            // multiplying by 5e22 to shift probabilities to a reasonable level
            probabilityMap.put(currentKnapsack, Math.exp(-currentKnapsack.getTotal() / (kB * temperature * 5e22)));
        }

        int i = 0;
        int populationSizeBeforeSelection = knapsacks.size();
        ArrayList<Knapsack> unchangedKnapsackList = knapsacks;

        while (knapsacks.size() > populationSizeBeforeSelection * 0.8) { // keep 80 % of the population alive

            if (mtf.nextBoolean(probabilityMap.get(unchangedKnapsackList.get(i)))) knapsacks.remove(i);

            i = (i + 1) % populationSizeBeforeSelection;
        }

        return knapsacks;
    }
}