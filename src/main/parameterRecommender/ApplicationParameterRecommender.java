package parameterRecommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationParameterRecommender {
    public static void main(String... args) throws IOException {
        //TODO Parameter für ParameterRecommenderApplication entweder aus main args oder mit Scanner zur Laufzeit einlesen

        ArrayList<String> cm = new ArrayList<>();
        cm.addAll(Arrays.asList("1PX", "2PX", "AX", "HX", "IX", "KPX", "SX", "UNX"));
        ArrayList<String> cr = new ArrayList<>();
        cr.addAll(Arrays.asList("0.5", "0.6", "0.7", "0.8"));
        ArrayList<String> mm = new ArrayList<>();
        mm.addAll(Arrays.asList("DM", "EM", "INSM", "INVM", "SM"));
        ArrayList<String> mr = new ArrayList<>();
        mr.addAll(Arrays.asList("0.001", "0.002", "0.003", "0.004", "0.005"));
        ArrayList<String> s = new ArrayList<>();
        //TSP: s.addAll(Arrays.asList("PRWS", "RBRWS", "RWS", "TS"));
        s.addAll(Arrays.asList("BS", "RS", "RWS", "TS"));


        for(int i = 0; i< 10000; i++) {


            int counter = 0;
            for(String Crossover : cm) {
                for(String CrossoverRatio : cr) {
                    for(int c = 0; c < 5; c++) {
                        for(int d = 0; d < 5; d++) {
                            //for(int e = 0; e < 4; e++) {
                            for(String selection : s)
                                switch (selection) {
                                    case "BS":
                                        // Execute Service BS Selection
                                        ExecutorService executor = execute();

                                        try {
                                            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                }
                        }
                    }
                }
            }
        }
        //TODO nach 10000 Iterationen einer bestimmten Parameter Konfiguration die Kapazität, Fitness usw. testen und dann optimale Parameter vorschlagen
    }

    public static ExecutorService execute(String Operation) { // bleibt immer gleich, nur Service ändert sich
        ExecutorService executor = Executors.newFixedThreadPool(Configuration.instance.numberOfProcessors);

        ArrayList<Integer> knapsack01Genes = new ArrayList<>();
        ArrayList<Integer> knapsack02Genes = new ArrayList<>();
        random.MersenneTwisterFast random = new random.MersenneTwisterFast();

        while(knapsack01Genes.size() < 150) {
            knapsack01Genes.add(random.nextInt(0, 1));
            knapsack02Genes.add(random.nextInt(0, 1));
        }
        base.Knapsack knapsack01 = new base.Knapsack();
        //knapsack01.setGeneList(knapsack01Genes);
        base.Knapsack knapsack02 = new base.Knapsack();
        //knapsack02.setGeneList(knapsack02Genes);

        if(Operation == MutationType.DM.name()) {
            Runnable worker = new ServiceDM(knapsack01, knapsack02);
        } else {
            //TODO Throw Exception
            Runnable worker = new Service(null, null);
        }

        executor.execute(worker);

        executor.shutdown();
        return executor;
    }

    public static class Service implements Runnable {
        //TODO Variablen einfügen
        base.Knapsack knapsack01;
        base.Knapsack knapsack02;

        public Service(base.Knapsack knapsack01, base.Knapsack knapsack02) {
            //TODO Konstruktor
            this.knapsack01 = knapsack01;
            this.knapsack02 = knapsack02;
        }

        public void run() {
            //TODO run füllen
            crossover.ArithmeticCrossover arithmeticCrossover = new crossover.ArithmeticCrossover();
            ArrayList<base.Knapsack> arithmeticChilds = arithmeticCrossover.doCrossover(knapsack01, knapsack02);
        }
    }

    public static class ServiceDM implements Runnable {
        //TODO Variablen einfügen
        base.Knapsack knapsack01;
        base.Knapsack knapsack02;

        public Service(base.Knapsack knapsack01, base.Knapsack knapsack02) {
            //TODO Konstruktor
            this.knapsack01 = knapsack01;
            this.knapsack02 = knapsack02;
        }

        public void run() {
            //TODO run füllen
            crossover.ArithmeticCrossover arithmeticCrossover = new crossover.ArithmeticCrossover();
            ArrayList<base.Knapsack> arithmeticChilds = arithmeticCrossover.doCrossover(knapsack01, knapsack02);
        }
    }
}
