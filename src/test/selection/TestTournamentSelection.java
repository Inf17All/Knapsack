package selection;

import base.Knapsack;
import base.Population;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestTournamentSelection {
    @Test
    public void testTournamentSelection() {
        Selection selection = new TournamentSelection();

        List<Knapsack> population = data.TestUtils.getTestPopulation();
        List<Knapsack> selectedPopulation = selection.doSelection((Population) population);

        //Test ob neue selektierte Population existiert
        Assertions.assertNotNull(selectedPopulation);

        //Test ob überhaupt etwas an der zu selektierenden Population verändert wurde
        Assertions.assertNotEquals(population, selectedPopulation);

        //Test ob Population veringert wurde
        Assertions.assertTrue(selectedPopulation.size() < population.size());
    }
}