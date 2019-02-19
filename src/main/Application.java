import base.Knapsack;
import crossover.ArithmeticCrossover;
import crossover.Crossover;
import crossover.ScatteredCrossover;
import data.HSQLDBManager;
import mutation.Mutation;
import selection.Selection;

public class Application {
    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;

    public static void main(String... args) {
        ArithmeticCrossover arithmeticCrossover = new ArithmeticCrossover();
        arithmeticCrossover.doCrossover(new Knapsack(), new Knapsack());
        ScatteredCrossover scatteredCrossover = new ScatteredCrossover();
        scatteredCrossover.doCrossover(new Knapsack(), new Knapsack());
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