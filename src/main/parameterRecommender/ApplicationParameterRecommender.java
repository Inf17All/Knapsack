package parameterRecommender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationParameterRecommender {
    public static void main(String... args) {
        Configuration.instance.scanMaxNumberOfIterations(); // Lese Parameter

        ExecutorService executor = execute();
        try {
            while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //wird 3200 mal aufgerufen
    public static ExecutorService execute() {
        ExecutorService executor = Executors.newFixedThreadPool(Configuration.instance.numberOfProcessors);

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

        for(String Crossover : cm) {
            for(String CrossoverRatio : cr) {
                for(String Mutation : mm) {
                    for(String MutationRatio : mr) {
                        for(String Selection : s) {
                            Runnable worker = new Service(Crossover, CrossoverRatio, Mutation, MutationRatio, Selection);
                            executor.execute(worker);
                        }
                    }
                }
            }
        }
        executor.shutdown();
        return executor;
    }

    public static class Service implements Runnable {
        private String Crossover;
        private String CrossoverRatio;
        private String Mutation;
        private String MutationRatio;
        private String Selection;
        //TODO Population einfügen

        public Service(String Crossover, String CrossoverRatio, String Mutation, String MutationRatio, String Selection) {
            this.Crossover = Crossover;
            this.CrossoverRatio = CrossoverRatio;
            this.Mutation = Mutation;
            this.MutationRatio = MutationRatio;
            this.Selection = Selection;
            //TODO Erzeuge Anfangspopulation
        }

        public void run() {
            //for(int i = 0; i< 10000; i++) {
            for(int i = 0; i< Configuration.MaximumNumberOfIterations; i++) { // Läuft so oft wie -i angegeben
                //TODO Configuration Name anpassen
                if(Crossover == "1PX") {
                    //TODO führe 1PX aus
                } //TODO Jeden Parameter Abfrage zund entsprechende Mthode ausführen
            }
            //TODO Auswertung der Fitness
        }
    }
}
