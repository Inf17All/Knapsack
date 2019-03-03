package selection;

import base.Knapsack;
import base.Population;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// BS
public class BoltzmannSelection extends Selection {

    private final double kB = 1.38064852e-23; // joule per kelvin

    public ArrayList<Knapsack> doSelection(Population population) {

        /*

        What's my temperature? Total fitness of population?
        What's my energy? Selection (1) or death (0)? Fitness?

         */

        ArrayList<Knapsack> knapsacks = population.getKnapsackList();

        MersenneTwisterFast mtf = new MersenneTwisterFast(System.nanoTime());

        // formula: p(e) = exp(-e/(kB*T))

        int temperature = knapsacks.stream().mapToInt(Knapsack::getTotal).sum(); // T: total fitness of pop

        for (Knapsack k : knapsacks) {

            int knapsackIndex = knapsacks.indexOf(k);

            double pe = Math.exp(-k.getTotal() / (kB * temperature));

            // remove if true because formula spits out high probabilities for less fit individuals
            if(mtf.nextBoolean(pe)) knapsacks.remove(k);
        }

        return knapsacks;
    }
}