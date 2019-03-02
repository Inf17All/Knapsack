package mutation;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInversionMutation {
    @Test
    public void testInversionMutation() {
        Mutation mutation = new InversionMutation();

        Knapsack knapsack = data.TestUtils.getTestKnapsack();
        mutation.doMutation(knapsack);

        Assertions.assertNotNull(knapsack);
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}
