import base.Knapsack;
import crossover.*;
import data.HSQLDBManager;
import mutation.Mutation;
import random.MersenneTwisterFast;
import selection.Selection;

import java.util.ArrayList;

public class Application {
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;

    public static void main(String... args) {
        ArrayList<Integer> knapsack01Genes = new ArrayList<>();
        ArrayList<Integer> knapsack02Genes = new ArrayList<>();
        MersenneTwisterFast random = new MersenneTwisterFast();

        while(knapsack01Genes.size() < 150) {
            knapsack01Genes.add(random.nextInt(0, 1));
            knapsack02Genes.add(random.nextInt(0, 1));
        }
        Knapsack knapsack01 = new Knapsack();
        knapsack01.setGeneList(knapsack01Genes);
        Knapsack knapsack02 = new Knapsack();
        knapsack02.setGeneList(knapsack02Genes);


        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        ArrayList<Knapsack> arithmeticChilds = arithmeticCrossover.doCrossover(knapsack01, knapsack02);

        ScatteredCrossover scatteredCrossover = new ScatteredCrossover();
        ArrayList<Knapsack> scatteredChilds = scatteredCrossover.doCrossover(knapsack01, knapsack02);

        IntermediateCrossover intermediateCrossover = new IntermediateCrossover();
        ArrayList<Knapsack> intermediateChilds = intermediateCrossover.doCrossover(knapsack01, knapsack02);

        HeuristicCrossover heuristicCrossover = new HeuristicCrossover();
        ArrayList<Knapsack> heuristicChilds = heuristicCrossover.doCrossover(knapsack01, knapsack02);

        System.out.println("");
        //Application application = new Application();
        //application.startupHSQLDB();
        //application.loadData();
        //application.initConfiguration();
        //application.execute();
        //application.shutdownHSQLDB();
    }

    public void startupHSQLDB() {
        HSQLDBManager.instance.startup();
        HSQLDBManager.instance.init();
    }

    public void shutdownHSQLDB() {
        HSQLDBManager.instance.shutdown();
    }

    public void loadData() {
    }

    public void initConfiguration() {
        System.out.println("--- GeneticAlgorithm.initConfiguration()");
        System.out.println();
    }

    public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");
        HSQLDBManager.instance.insert("hello world");
    }
}