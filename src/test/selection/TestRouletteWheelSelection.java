package selection;

import base.Knapsack;
import base.Population;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestRouletteWheelSelection {
    @Test
    public void testRouletteWheelSelection() {
        Selection selection = new RouletteWheelSelection();

        List<Knapsack> population = data.TestUtils.getTestPopulation();
        List<Knapsack> selectedPopulation = selection.doSelection((Population) population);

        Assertions.assertNotNull(selectedPopulation);
        Assertions.assertNotEquals(population, selectedPopulation);
    }
}