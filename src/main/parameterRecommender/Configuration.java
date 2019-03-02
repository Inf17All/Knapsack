package parameterRecommender;

import java.util.Scanner;

public enum Configuration {
    instance;

    public int numberOfCores = Runtime.getRuntime().availableProcessors();
    public int maxIterations = 1000000;

    public void setMaxIterations(int iterations){
        this.maxIterations = iterations;
    }

    public void scanMaxNumberOfIterations(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Set maximum number of iterations:");
        this.maxIterations = scanner.nextInt();

        scanner.close();
    }
}
