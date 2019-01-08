package mutation;

import base.Knapsack;

public abstract class Mutation {
    public abstract void doMutation(Knapsack knapsack);

    public String toString() {
        return getClass().getSimpleName();
    }
}