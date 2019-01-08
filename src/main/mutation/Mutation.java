package mutation;

import base.Knapsack;

public abstract class Mutation {
    public abstract void doMutation(Knapsack knapsack);

    public String toString() {
        return getClass().getSimpleName();
    }

    protected getRandomNumber(Knapsack knapsack){
        //int len = knapsack.items.size();


    }
}