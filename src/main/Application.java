import base.Population;
import configuration.Configuration;
import crossover.*;
import mutation.*;
import selection.*;
import data.HSQLManagerForEvolution.HSQLManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;

    public static void main(String... args) {
        Application application = new Application();
      //  application.startupHSQLDB();
       // application.loadData();
        application.initConfiguration();
        application.execute();
       // application.shutdownHSQLDB();
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

        int generation = 0;
        int chromosome[]= {1,2,3,4};
        Population population = new Population();



            String selectionAlgorithm;
            String mutationAlgorithm;
            String crossoverAlgorithm;



        Scanner inputReader = new Scanner(new InputStreamReader(System.in));

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
        //System.out.print("crossover ratio (0.50-0.80): ");
        //Configuration.instance.crossoverRatio = inputReader.nextDouble();

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
        //System.out.print("mutation ratio (0.001-0.0005): ");
        //Configuration.instance.mutationRatio = inputReader.nextDouble();

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

        for ( int i= 0; i < Configuration.instance.sizeOfPopulation ; i++){
            for( int j= 0 ; j < Configuration.instance.numberOfItems  ; j++){

            }

        }



        }





    }
