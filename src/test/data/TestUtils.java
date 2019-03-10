package data;

import base.Item;
import base.Knapsack;

import java.util.ArrayList;
import java.util.List;

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

    public static Knapsack getTestKnapsackSecond() {
        Item item1 = new Item("item1", 1, 1);
        Item item2 = new Item("item2", 1, 2);
        Item item3 = new Item("item3", 1, 3);
        Item item4 = new Item("item4", 1, 4);

        Knapsack knapsack = new Knapsack();

        knapsack.addItem(item4);
        knapsack.addItem(item3);
        knapsack.addItem(item2);
        knapsack.addItem(item1);

        return knapsack;
    }

    public static List<Knapsack> getTestPopulation() {
        List<Knapsack> population = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            population.add(getTestKnapsack());
        }
        return population;
    }
}
