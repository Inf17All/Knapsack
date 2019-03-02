package mutation;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestScrambleMutation {
    @Test
    public void testScrambleMutation() {
        Mutation mutation = new ScrambleMutation();

        Knapsack knapsack = data.TestUtils.getTestKnapsack();
        mutation.doMutation(knapsack);

        Assertions.assertNotNull(knapsack);
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}