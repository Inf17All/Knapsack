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

        //Test ob Knapsack noch vorhanden
        Assertions.assertNotNull(knapsack);

        //Test ob Knapsack noch ursprüngliches Format hat
        Assertions.assertTrue(knapsack.getGeneList().size() == data.TestUtils.getTestKnapsack().getGeneList().size());

        //Test ob überhaupt etwas mutiert wurde
        Assertions.assertNotEquals(knapsack, data.TestUtils.getTestKnapsack());
    }
}