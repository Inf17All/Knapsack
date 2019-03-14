package mutation;

import base.Knapsack;
import java.util.ArrayList;


public class InsertionMutation extends Mutation {
    public ArrayList<Integer> doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = knapsack.getGeneList();
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();

        int[] range = getRandomRange(knapsack,false);
        //Second index is the selected number and first index is the place to insert the number.

        int i = 0;
        if(range[0]<range[1]){
            while(i<range[0]){
                valuesNew.add(values.get(i));
                i++;
            }
            valuesNew.add(values.get(range[1]));
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
            while(i<range[1]){
                valuesNew.add(values.get(i));
                i++;
            }
            i++;
            while(i<range[0]+1){
                valuesNew.add(values.get(i));
                i++;
            }
            valuesNew.add(range[1]);
            while(i<values.size()){
                valuesNew.add(values.get(i));
                i++;
            }
        }
        return valuesNew;
    }
}