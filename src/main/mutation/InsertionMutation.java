package mutation;

import base.Knapsack;
import java.util.ArrayList;

// INSM
public class InsertionMutation extends Mutation {
    public ArrayList<Integer> doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = knapsack.getGeneList();
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();
        int[] range = getRandomRange(knapsack,false);
        int i = 0;

        if(range[0]<range[1]){
            while(i<=range[0]){
                valuesNew.add(values.get(i));
                i++;
            }
            valuesNew.add(values.get(range[1]));
            i++;
            while(i<values.size()){
                if(i != range[1]) {
                    valuesNew.add(values.get(i));
                    i++;
                }
                else{
                    i++;
                }
            }
        }else{
            //REST

        }
        return valuesNew;
    }
}