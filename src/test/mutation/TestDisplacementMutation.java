package mutation;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDisplacementMutation {
    @Test
    public void testDisplacementMutation() {
        Mutation mutation = new DisplacementMutation();

        Knapsack knapsack = data.TestUtils.getTestKnapsack();
        mutation.doMutation(knapsack);

        Assertions.assertNotNull(knapsack);
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}