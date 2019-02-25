package mutation;

import base.Knapsack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExchangeMutation {
    @Test
    public void testExchangeMutation() {
        Mutation mutation = new ExchangeMutation();

        Knapsack knapsack = data.TestUtils.getTestKnapsack();
        mutation.doMutation(knapsack);

        Assertions.assertNotNull(knapsack);
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}