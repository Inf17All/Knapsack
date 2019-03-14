package mutation;

import base.Knapsack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// SM
public class ScrambleMutation extends Mutation {
    public ArrayList<Integer> doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = knapsack.getGeneList();
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();
        int[] range = getRandomRange(knapsack,true);

        int i = 0;
        while(i<range[0]){
            valuesNew.add(values.get(i));
            i++;
        }
        List<Integer> shuffledRange = new ArrayList<>();
        while(i>=range[0] && i<=range[1]){
            shuffledRange.add(values.get(i));
            i++;
        }
        Collections.shuffle(shuffledRange);
        valuesNew.addAll(shuffledRange);
        while(i<values.size()){
            valuesNew.add(values.get(i));
            i++;
        }

        return valuesNew;
    }
}