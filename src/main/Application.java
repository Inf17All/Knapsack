import crossover.Crossover;
import mutation.Mutation;
import selection.Selection;
import data.HSQLManagerForEvolution.HSQLManager;

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
    }

    public void execute() {
        System.out.println("--- GeneticAlgorithm.execute()");
    }
}