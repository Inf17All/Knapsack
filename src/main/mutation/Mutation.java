package mutation;

import base.Knapsack;
import base.Item;
import random.MersenneTwisterFast;
import java.util.ArrayList;

public abstract class Mutation {
    public abstract ArrayList<Integer> doMutation(Knapsack knapsack);
    MersenneTwisterFast rdm = new MersenneTwisterFast();

    public String toString() {
        return getClass().getSimpleName();
    }

    //Gibt eine zufällige Range der ArrayList mit items aus
    //plus die größe der range
    //Hierbei ist die erste Zahl des return-Arrays kleiner als die 2. solange ordered auf true gesetzt ist
    protected int[] getRandomRange(Knapsack knapsack, boolean ordered){
        int[] tempArray = new int[2];
        ArrayList<Integer> items = knapsack.getGeneList();

        int len = items.size();
        int firstRdm = rdm.nextInt(0,len-1);
        int secondRdm;

        do{
            secondRdm = rdm.nextInt(0,len-1);
        }
        while(firstRdm == secondRdm);

        if(ordered) {
            if (firstRdm < secondRdm) {
                tempArray[0] = firstRdm;
                tempArray[1] = secondRdm;
            } else {
                tempArray[0] = secondRdm;
                tempArray[1] = firstRdm;
            }
        }else{
            tempArray[0] = firstRdm;
            tempArray[1] = secondRdm;
        }
        return tempArray;
    }
}