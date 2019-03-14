package mutation;

import base.Knapsack;

import java.util.ArrayList;

// INVM
public class InversionMutation extends Mutation {

    public ArrayList<Integer> doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = knapsack.getGeneList();
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();
        int[] range = getRandomRange(knapsack,true);
        int rangelen = range[1] - range[0];


        int i = 0;
        while(i<range[0]){
            valuesNew.add(values.get(i));
            i++;
        }

        int temp = i;
        while(i>=range[0] && i<=range[1]){
            valuesNew.add(values.get(rangelen+temp));
            rangelen--;
            i++;
        }

        while(i<values.size()){
            valuesNew.add(values.get(i));
            i++;
        }
        return valuesNew;
    }
}