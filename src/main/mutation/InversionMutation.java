package main.mutation;

import main.base.Knapsack;
import main.base.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

// INVM
public class InversionMutation extends Mutation {

    public void doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = getValueList(knapsack);
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();
        int[] range = getRandomRange(knapsack);
        int i = 0;
        while(i<range[0]){
            valuesNew.add(values.get(i));
            i++;
        }
        while(i>=range[1]){
            valuesNew.add(values.get(range[2]-range[1]));
            range[2]--;
            i++;
        }
        while(i<values.size()){
            valuesNew.add(values.get(i));
            i++;
        }
        //return valuesNew;
    }
}