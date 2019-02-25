package mutation;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInsertionMutation {
    @Test
    public void testInsertionMutation() {
        Mutation mutation = new InsertionMutation();

        Knapsack knapsack = data.TestUtils.getTestKnapsack();
        mutation.doMutation(knapsack);

        Assertions.assertNotNull(knapsack);
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}