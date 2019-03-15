package configuration;

import crossover.Crossover;
import mutation.Mutation;
import random.MersenneTwisterFast;
import selection.Selection;
import base.Item;

import java.util.ArrayList;

public enum Configuration {
    instance;
    public MersenneTwisterFast randomGenerator = new MersenneTwisterFast(System.currentTimeMillis());
    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;

    public String dataFilePath = dataDirectory + "knapsack_instance.csv";

    public String databaseFile = dataDirectory + "datastore.db";

    public MersenneTwisterFast randomNumberGenerator = new MersenneTwisterFast(System.currentTimeMillis());

    public ArrayList<Item> itemList = new ArrayList<>();

    public Integer fitness;

    // base
    public int maximumKnapsackCapacity = 0;
    public int numberOfItems = 0;
    public ArrayList<Integer> weightList = new ArrayList<>();
    public ArrayList<Integer> valueList = new ArrayList<>();
    public int totalValue = 0;
    public double offset = 0;
    public double penalty = 0;
    public int sizeOfPopulation = 100;
    public int maximumNumberOfGenerations = 1000;

    // selection
    public Selection selection;
    public double tournamentKValue;

    // crossover
    public Crossover crossover;
    public double crossoverRatio = 0.7;
    public int numberOfNCrossoverSlicePoints = 2;

    // mutation
    public Mutation mutation;
    public double mutationRatio = 0.01;
    public int numberOfNMutationPoints = 2;

    // solution
    public Integer[] optimalSolution;
    public StringBuilder optimalSolutionString = new StringBuilder();
    public int optimalSolutionWeight = 0;
    public int optimalSolutionValue = 0;
    public double optimalSolutionFitness = 0;

}