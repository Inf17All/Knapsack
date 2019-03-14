package selection;

import configuration.Configuration;
import base.Knapsack;
import base.Population;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


// RWS
public class RouletteWheelSelection extends Selection {
    public ArrayList<Knapsack> doSelection(Population population) {
         double totalSum = 0;


      // sum
        for (Knapsack knapsack: population.getKnapsackList()) {
            totalSum +=knapsack.getTotal();
        }
        SortedMap<Double,Knapsack> totalRelativity = new TreeMap<>();

        for (Knapsack knapsack:population.getKnapsackList()) {
                double key = knapsack.getTotal() / totalSum;
                totalRelativity.put(key, knapsack);
            }
        SortedMap<Double,Knapsack> num = new TreeMap<>();
        double previousProbability = 0;
        for (Map.Entry<Double,Knapsack> entry: totalRelativity.entrySet()){
            double currentProbability=previousProbability+entry.getKey();
            num.put(currentProbability,entry.getValue());
            previousProbability = currentProbability;
        }

        ArrayList<Knapsack> order = new ArrayList<>();
        while (!num.isEmpty()){
            double randomNumber = new Random.MersenneTwisterFast().nextDouble(0,num.lastKey());
            for (Map.Entry<Double,Knapsack> selecktion :num.entrySet()){
                if (randomNumber <= selecktion.getKey()){
                    order.add(selecktion.getValue());
                    num.remove(selecktion.getKey());
                    break;
                }
            }
        }

        return  order;
    }
}
