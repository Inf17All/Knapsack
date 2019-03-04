package data;

import base.Knapsack;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    public static Knapsack getTestKnapsack() {
        ArrayList<Integer> geneList = new ArrayList<>();
        geneList.add(1);
        geneList.add(2);
        geneList.add(3);
        geneList.add(4);

        Knapsack knapsack = new Knapsack();
        knapsack.setGeneList(geneList);

        return knapsack;
    }

    public static Knapsack getTestKnapsackSecond() {
        ArrayList<Integer> geneList = new ArrayList<>();
        geneList.add(4);
        geneList.add(3);
        geneList.add(2);
        geneList.add(1);

        Knapsack knapsack = new Knapsack();
        knapsack.setGeneList(geneList);

        return knapsack;
    }

    public static List<Knapsack> getTestPopulation() {
        List<Knapsack> population = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            population.add(getTestKnapsack());
        }
        population.add(getTestKnapsackSecond());
        return population;
    }
}
