package crossover;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestIntermediateCrossover {
    @Test
    public void testIntermediateCrossover() {
        Crossover crossover = new IntermediateCrossover();

        Knapsack knapsack1 = data.TestUtils.getTestKnapsack();
        Knapsack knapsack2 = data.TestUtils.getTestKnapsack();

        List<Knapsack> crossoverKnapsack = crossover.doCrossover(knapsack1, knapsack2);

        Assertions.assertNotNull(crossoverKnapsack);
        Assertions.assertFalse(crossoverKnapsack.contains(knapsack1));
        Assertions.assertFalse(crossoverKnapsack.contains(knapsack2));
    }
}