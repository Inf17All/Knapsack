package parameterRecommender;

public enum Configuration {
    instance;

    public int numberOfCores = Runtime.getRuntime().availableProcessors();
    public int maxIterations = 1000000;

    public void setMaxIterations(int iterations){
        this.maxIterations = iterations;
    }
}
