package crossover;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestHeuristicCrossover {
    @Test
    public void testHeuristicCrossover() {
        Crossover crossover = new HeuristicCrossover();

        Knapsack knapsack1 = data.TestUtils.getTestKnapsack();
        Knapsack knapsack2 = data.TestUtils.getTestKnapsack();

        List<Knapsack> crossoverKnapsack = crossover.doCrossover(knapsack1, knapsack2);

        //Überprüfen ob crossover Ergebnis vorhanden
        Assertions.assertNotNull(crossoverKnapsack);

        //Überprüfen ob crossover nicht einfach aus den beiden Parents besteht
        Assertions.assertFalse(crossoverKnapsack.contains(knapsack1));
        Assertions.assertFalse(crossoverKnapsack.contains(knapsack2));

        //Überprüfen ob Kinder aus den beiden Parents entstehen konnten
        for (Knapsack knapsack : crossoverKnapsack) {
            for (int i = 0; i < knapsack.getGeneList().size(); i++) {
                Assertions.assertTrue(knapsack1.getGeneList().get(i).equals(knapsack.getGeneList().get(i)) || knapsack2.getGeneList().get(i).equals(knapsack.getGeneList().get(i)));
            }
        }
    }
}