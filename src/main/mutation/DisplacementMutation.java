package mutation;

import base.Knapsack;
import java.util.ArrayList;

// DM
public class DisplacementMutation extends Mutation {
    public ArrayList<Integer> doMutation(Knapsack knapsack) {
        ArrayList<Integer> values = knapsack.getGeneList();
        ArrayList<Integer> valuesNew = new ArrayList<Integer>();

        //decide if Displacement left or right --> false = left | true = right
        boolean displacementDirection = rdm.nextBoolean();
        int displacementRangeNewStart;
        int[] range;

        //Get a in displacementDirection shiftable range
        do {
            range = getRandomRange(knapsack, true);
        }
        while((!displacementDirection && range[0] == 0) || (displacementDirection && range[1] == values.size()-1) );

        int rangelen = range[1] - range[0] + 1;


        //Shift Range left
        if(!displacementDirection){
            displacementRangeNewStart = rdm.nextInt(range[0]);
            System.out.println(displacementRangeNewStart);

            int i = 0;
            while(i<displacementRangeNewStart) {
                valuesNew.add(values.get(i));
                i++;
            }
            int i2 = 0;
            while(i2<rangelen){
                valuesNew.add(values.get(i2+range[0]));
                i2++;
            }
            while(valuesNew.size() < values.size()){
                if( !(i>=range[0] && i<=range[1]) ) {
                    valuesNew.add(values.get(i));
                }
                i++;
            }
        }
        //Shift Range right
        else{
            displacementRangeNewStart = range[0] + rdm.nextInt(values.size()-1 - range[1]) + 1;
            System.out.println(displacementRangeNewStart);

            int i = 0;
            while(i<range[0]) {
                valuesNew.add(values.get(i));
                i++;
            }
            while(i<displacementRangeNewStart){
                valuesNew.add(values.get(i + rangelen));
                i++;
            }
            int i2 = 0;
            while(i2<rangelen){
                valuesNew.add(values.get(range[0] + i2));
                i2++;
            }
            while(valuesNew.size() < values.size()){
                valuesNew.add(values.get(i+rangelen));
                i++;
            }
        }

        return valuesNew;
    }
}