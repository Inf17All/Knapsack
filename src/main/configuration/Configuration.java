package configuration;

import random.MersenneTwisterFast;

public enum Configuration {
    instance;

    public String fileSeparator = System.getProperty("file.separator");
    public String userDirectory = System.getProperty("user.dir");

    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String dataFilePath = dataDirectory + "knapsack_instance.csv";

    public String databaseFile = dataDirectory + "datastore.db";

    public MersenneTwisterFast randomNumberGenerator = new MersenneTwisterFast(System.currentTimeMillis());

    public CrossoverType crossoverType = CrossoverType.KPX;
    public MutationType mutationType = MutationType.EM;
    double crossoverRatio = 0.7;
    double mutationRatio = 0.001;
}