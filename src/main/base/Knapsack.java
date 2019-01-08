package base;

import java.util.ArrayList;

public class Knapsack {
    private ArrayList<Item> items = new ArrayList<>();

    public Knapsack() {
    }

    public Knapsack(Knapsack knapsack) {
        for (Item item : knapsack.getItems())
            items.add(item);
    }

    public int getTotal() {
        int total = 0;

        for (int i = 0; i < items.size(); i++)
            total += items.get(i).getValue();
        return total;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : items)
            stringBuilder.append(item.getName() + " ");
        stringBuilder.append(" - ").append("total: ").append(getTotal());
        return stringBuilder.toString();
    }
}