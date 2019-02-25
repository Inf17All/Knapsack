package data;

import base.Item;
import base.Knapsack;
import base.Population;

public class TestUtils {
    public static Knapsack getTestKnapsack() {
        Item item1 = new Item("item1", 1, 1);
        Item item2 = new Item("item2", 1, 2);
        Item item3 = new Item("item3", 1, 3);
        Item item4 = new Item("item4", 1, 4);

        Knapsack knapsack = new Knapsack();

        knapsack.addItem(item1);
        knapsack.addItem(item2);
        knapsack.addItem(item3);
        knapsack.addItem(item4);

        return knapsack;
    }

    public static Population getTestPopulation() {
        Population population = new Population();
        System.out.println("Testpopulation is empty");
        return population;
    }
}
