package selection;

import base.Knapsack;
import base.Population;
import random.MersenneTwisterFast;

import java.util.ArrayList;

// TS

/*
since an ArrayList<Knapsack> needs to be returned, I have decided to simply put
the better Challenger further in front of the ArrayList.
*/
public class TournamentSelection extends Selection {
    public ArrayList<Knapsack> doSelection(Population population) {

        //create ArrayLists and Randomizer
        ArrayList<Knapsack> knapsackList = population.getKnapsackList();
        ArrayList<Knapsack> result = population.getKnapsackList();
        MersenneTwisterFast r = new MersenneTwisterFast();

        //select Challengers
        int indexFirstChallenger = r.nextInt(1,knapsackList.size());
        int indexSecondChallenger = r.nextInt(1,knapsackList.size());

        //get Challenger genes
        Knapsack firstChallenger = knapsackList.get(indexFirstChallenger);
        Knapsack secondChallenger = knapsackList.get(indexSecondChallenger);

        //get Challenger values
        int firstValue =  firstChallenger.getTotal();
        int secondValue = secondChallenger.getTotal();

        //compare Challenger values and swap if needed
        if(firstValue<secondValue){
            if(indexFirstChallenger<indexSecondChallenger) {
                result.add(indexFirstChallenger, secondChallenger);
                result.add(indexSecondChallenger, firstChallenger);
            }
        }else{
            if(indexFirstChallenger>indexSecondChallenger){
                result.add(indexSecondChallenger, firstChallenger);
                result.add(indexFirstChallenger, secondChallenger);
            }
        }

        return result;
    }
}