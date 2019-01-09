package mutation;

import base.Knapsack;
import base.Item;
import random.MersenneTwisterFast;
import java.util.ArrayList;

public abstract class Mutation {
    public abstract void doMutation(Knapsack knapsack);
    MersenneTwisterFast rdm = new MersenneTwisterFast();

    public String toString() {
        return getClass().getSimpleName();
    }

    //Gibt zufälligen Index der ArrayList mit items aus
    protected int getRandomIndex(Knapsack knapsack){
        ArrayList<Item> items = knapsack.getItems();
        int len = items.size();
        return rdm.nextInt(0,len-1);
    }

    //Gibt eine zufällige Range der ArrayList mit items aus
    //Hierbei ist die erste Zahl des return-Arrays kleiner als die 2.
    protected int[] getRandomRange(Knapsack knapsack){
        int[] tempArray = new int[2];
        ArrayList<Item> items = knapsack.getItems();
        int len = items.size();
        int firstRdm = rdm.nextInt(0,len-1);
        int secondRdm;
        do{
            secondRdm = rdm.nextInt(0,len-1);
        }
        while(firstRdm == secondRdm);
        if(firstRdm<secondRdm){
            tempArray[0] = firstRdm;
            tempArray[1] = secondRdm;
        }
        else{
            tempArray[0] = secondRdm;
            tempArray[1] = firstRdm;
        }
        return tempArray;
    }
}