package selection;

import base.Knapsack;
import base.Population;

import java.util.ArrayList;

public abstract class Selection {
    public abstract ArrayList<Knapsack> doSelection(Population population);


}