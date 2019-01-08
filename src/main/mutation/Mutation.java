package mutation;

import base.Knapsack;
import base.Item;
import java.util.ArrayList;

public abstract class Mutation {
    public abstract void doMutation(Knapsack knapsack);

    public String toString() {
        return getClass().getSimpleName();
    }

    protected int getRandomNumber(Knapsack knapsack){
        ArrayList<Item> items = knapsack.getItems();
        int num=0;
        int len = items.size();

        return num;
    }
}