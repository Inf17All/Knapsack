import configuration.Configuration;
import crossover.*;
import mutation.*;
import selection.*;
import data.HSQLManagerForEvolution.HSQLManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Application {
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;

    public static void main(String... args) {
        Application application = new Application();
        application.startupHSQLDB();
        application.loadData();
        application.initConfiguration();
        application.execute();
        application.shutdownHSQLDB();
    }

    public void startupHSQLDB() {
        HSQLManager.Knapsack.startup();
    }

    //Drops tables
    public void initHSQLDB(){
        HSQLManager.Knapsack.init();
    }

    public void shutdownHSQLDB() {
        HSQLManager.Knapsack.close();
    }

    public void loadData() {
    }

    public void initConfiguration() {
        System.out.println("--- GeneticAlgorithm.initConfiguration()");
        System.out.println();

        String selectionAlgorithm = null;
        String crossoverAlgorithm;
        String mutationAlgorithm;
        Scanner inputReader = new Scanner(System.in);

        // base
        System.out.print("population size (50-300): ");
        Configuration.instance.sizeOfPopulation = inputReader.nextInt();

        System.out.print("maximum number of generations: ");
        Configuration.instance.maximumNumberOfGenerations = inputReader.nextInt();

        // selection
        System.out.print("selection algorithm (RWS = roulette, TS = tournament, RS = rank , BS = boltzmann): ");
        selectionAlgorithm = inputReader.next();

        if (selectionAlgorithm == "RWS") {
            Configuration.instance.selection = new RouletteWheelSelection();
        } else if (selectionAlgorithm == "TS") {
            Configuration.instance.selection = new TournamentSelection();
            System.out.print("k-value for tournament selection (): ");
            Configuration.instance.tournamentKValue = inputReader.nextDouble();
        } else if (selectionAlgorithm == "RS") {
            Configuration.instance.selection = new RankSelection();
        } else if (selectionAlgorithm == "BS") {
            Configuration.instance.selection = new BoltzmannSelection();
        }

        // crossover
        System.out.print("crossover ratio (0.50-0.80): ");
        Configuration.instance.crossoverRatio = inputReader.nextDouble();

        System.out.print("crossover algorithm (AX = arithmetic, IX = intermediate , KPX = KPoint , 1PX = OnePoint , 2PX = TwoPoint , UNX = uniform , HX = heuristic , SX = scattered): ");
        crossoverAlgorithm = inputReader.next();

        if (crossoverAlgorithm == "AX") {
            Configuration.instance.crossover = new ArithmeticCrossover();

            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "IX") {
            Configuration.instance.crossover = new IntermediateCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "KPX") {
            Configuration.instance.crossover = new KPointCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "1PX") {
            Configuration.instance.crossover = new OnePointCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "2PX") {
            Configuration.instance.crossover = new TwoPointCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "UNX") {
            Configuration.instance.crossover = new UniformCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "HX") {
            Configuration.instance.crossover = new HeuristicCrossover();
            Configuration.instance.numberOfNCrossoverSlicePoints = inputReader.nextInt();
        } else if (crossoverAlgorithm == "SX") {
            Configuration.instance.crossover = new ScatteredCrossover();
        }

        // mutation
        System.out.print("mutation ratio (0.001-0.0005): ");
        Configuration.instance.mutationRatio = inputReader.nextDouble();

        System.out.print("mutation algorithm (DM = displacement, EM = exchange, SM = scramble , INSM = insert , INVM = inverse): ");
        mutationAlgorithm = inputReader.next();

        if (mutationAlgorithm == "DM") {
            Configuration.instance.mutation = new DisplacementMutation();
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == "EM") {
            Configuration.instance.mutation = new ExchangeMutation();
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == "SM") {
            Configuration.instance.mutation = new ScrambleMutation();
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == "INSM") {
            Configuration.instance.mutation = new InsertionMutation();
            Configuration.instance.numberOfNMutationPoints = inputReader.nextInt();
        } else if (mutationAlgorithm == "INVM") {
            Configuration.instance.mutation = new InversionMutation();
        }
    }

    public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");

        try {
            // capacity
            Scanner capacityScanner = new Scanner(new File(Configuration.instance.dataDirectory + "capacity.txt"));
            Configuration.instance.maximumKnapsackCapacity = capacityScanner.nextInt();
            capacityScanner.close();

            // weight
            Scanner weightScanner = new Scanner(new File(Configuration.instance.dataDirectory + "weight.txt"));
            while (weightScanner.hasNextInt()) {
                Configuration.instance.numberOfItems++;
                int weight = weightScanner.nextInt();
                Configuration.instance.weightList.add(weight);
            }
            weightScanner.close();

            //  value
            Scanner valueScanner = new Scanner(new File(Configuration.instance.dataDirectory + "value.txt"));
            while (valueScanner.hasNextInt()) {
                int value = valueScanner.nextInt();
                Configuration.instance.valueList.add(value);
                Configuration.instance.totalValue += value;
            }
            valueScanner.close();

            Integer tempChild;
            for (int i = 0; i < Configuration.instance.numberOfItems; i++) {
                Configuration.instance.offset += Configuration.instance.valueList.get(i);
                tempChild = (Configuration.instance.valueList.get(i)) / Configuration.instance.weightList.get(i);
                if (tempChild > Configuration.instance.penalty)
                    Configuration.instance.penalty = tempChild;
            }

            Configuration.instance.offset *= 0.1;
            Scanner solScanner = new Scanner(new File(Configuration.instance.dataDirectory + "solution.txt"));
            Configuration.instance.optimalSolution = new Integer[Configuration.instance.numberOfItems];

            //for SChleife?

            //  Configuration.instance.optimalSolutionFitness = (double) getFitness(Configuration.instance.optimalSolution, Configuration.instance.optimalSolutionWeight);

            solScanner.close();
        } catch (
                IOException e) {
            System.err.println(e.getMessage());
        }


        int[][] chromosomePool = new int[Configuration.instance.sizeOfPopulation][Configuration.instance.numberOfItems];
        double[] fitnessPool = new double[Configuration.instance.sizeOfPopulation];
        int[] weightPool = new int[Configuration.instance.sizeOfPopulation];

        //FOR-Schleife?

        // main loop
        double fitnessSoFar = 0;

        for (int currentGeneration = 0; currentGeneration < Configuration.instance.maximumNumberOfGenerations; currentGeneration++) {
            // selection
            // int[] selectedParentPool = Configuration.instance.selection.doSelection();

            // crossover
            //  int[][] childPool = Configuration.instance.crossover.doCrossover();

            // mutation
          /*  for (int i = 0; i < Configuration.instance.sizeOfPopulation; i++)
                if (Configuration.instance.randomGenerator.nextDouble() < Configuration.instance.mutationRatio)
                    childPool[i] = Configuration.instance.mutation.doMutation();

            int currentBestIndex = 0;
            int globalBestIndex = 1;

            for (int i = 1; i < Configuration.instance.sizeOfPopulation; i++) {
                if (fitnessPool[i] > fitnessPool[currentBestIndex]) {
                    globalBestIndex = currentBestIndex;
                    currentBestIndex = i;
                } else if (fitnessPool[i] > fitnessPool[globalBestIndex]) {
                    globalBestIndex = i;
                }
            }*/
/*
            if (fitnessPool[currentBestIndex] > fitnessSoFar) {
                fitnessSoFar = fitnessPool[currentBestIndex];
                if (currentGeneration > 100) {
                    double percent = (fitnessPool[0] / Configuration.instance.optimalSolutionFitness) * 100;
                    System.out.printf(currentGeneration + " - best fitness : %,.2f%% of known optimum\n", percent);
                }
            }

            childPool[0] = chromosomePool[currentBestIndex];
            childPool[1] = chromosomePool[globalBestIndex];
            double tempFitness = fitnessPool[globalBestIndex];
            fitnessPool[0] = fitnessPool[currentBestIndex];
            fitnessPool[1] = tempFitness;
            int tempWeight = weightPool[globalBestIndex];
            weightPool[0] = weightPool[currentBestIndex];
            weightPool[1] = tempWeight;
            chromosomePool = childPool;

            for (int i = 2; i < Configuration.instance.sizeOfPopulation; i++) {
                weightPool[i] = 0;
                for (int j = 0; j < Configuration.instance.numberOfItems; j++) {
                    if (chromosomePool[i][j] != 0)
                        weightPool[i] += Configuration.instance.weightList.get(j);
                }
                fitnessPool[i] = fitness(chromosomePool[i], weightPool[i]);
            }
        }*/


        }
/*
    public double fitness(int[] chromosome, int s) {
        int value = 0;
        for (int i = 0; i < Configuration.instance.numberOfItems; i++)
            if (chromosome[i] != 0)
                value += Configuration.instance.valueList.get(i);
        if (s > Configuration.instance.maximumKnapsackCapacity) {
            double result = value - ((s - Configuration.instance.maximumKnapsackCapacity) * Configuration.instance.penalty + Configuration.instance.offset);
            if (result < 0.1)
                return 0.1;
            else
                return result;
        } else
            return value;
    }*/



    }
}