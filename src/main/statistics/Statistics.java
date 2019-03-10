package statistics;

import data.HSQLManagerForEvolution.EvaluationFactory;
import data.HSQLManagerForEvolution.HSQLManager;
import data.HSQLManagerForEvolution.IEvaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Statistics {
    private static HSQLManager database;

    public static void main(String[] args) {
        StringBuilder line = new StringBuilder();

        for (String argument : args)
            line.append(argument);

        String[] pars = line.toString().split(Configuration.instance.splitSymbol);

        if (pars.length != 5)
            throw new IllegalArgumentException("Wrong number of arguments.");

        if (!pars[1].toLowerCase().equals(Configuration.instance.firstArgument))
            throw new IllegalArgumentException("Wrong argument -" + pars[0]);

        try {
            getDatabase(pars[2]);
            getStatisticResult(pars[4], getListOfScenarios(pars[3]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getStatisticResult(String statisticMode, List<Integer> scenarioIDs) {
        int endOfWord = statisticMode.length();
        for (int i = 0; i < statisticMode.length(); i++) {
            if (!Character.isAlphabetic(statisticMode.charAt(i))) {
                endOfWord = i;
                break;
            }
        }

        for (int scenarioID : scenarioIDs) {
            Set<Double> evaluations = new TreeSet<>(new DoubleComparator());
            getScenarioEvaluation(scenarioID).forEach(element -> evaluations.add((double) element.getValue()));
            List<Double> evaluationDouble = new ArrayList<>(evaluations);
            System.out.println("Scenario " + scenarioID + " in " + database.toString());

            switch (statisticMode.substring(0, endOfWord).toLowerCase()) {
                case Configuration.median:
                    if (statisticMode.toLowerCase().equals(Configuration.median))
                        System.out.println(Configuration.median + ": " + StatisticsHelper.calculateMedian(evaluationDouble));
                    else
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    break;

                case Configuration.mean:
                    if (statisticMode.toLowerCase().equals(Configuration.mean))
                        System.out.println(Configuration.mean + ": " + StatisticsHelper.calculateMean(evaluationDouble));
                    else
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    break;

                case Configuration.mode:
                    if (statisticMode.toLowerCase().equals(Configuration.mode))
                        System.out.println(Configuration.mode + ": " + StatisticsHelper.calculateMode(evaluationDouble));
                    else
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    break;

                case Configuration.quantile:
                    if (statisticMode.toLowerCase()
                            .substring(Configuration.quantile.length(), Configuration.quantile.length() + 1)
                            .equals(Configuration.instance.equalSymbol)) {
                        try {
                            double quantile = Double.parseDouble(statisticMode.substring(Configuration.quantile.length() + Configuration.instance.equalSymbol.length()));
                            System.out.println(Configuration.quantile + ": " + StatisticsHelper.calculateQuantile(evaluationDouble, quantile));
                        } catch (Exception e) {
                            throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                        }
                    } else {
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    }
                    break;

                case Configuration.range:
                    if (statisticMode.toLowerCase().equals(Configuration.range)) {
                        System.out.println(Configuration.range + ": " + StatisticsHelper.calculateRange(evaluationDouble));
                        System.out.println("With range from " + evaluationDouble.get(0) + " to " + evaluationDouble.get(evaluationDouble.size() - 1));
                    } else
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    break;

                case Configuration.iqr:
                    if (statisticMode.toLowerCase()
                            .substring(Configuration.iqr.length(), Configuration.iqr.length() + 1)
                            .equals(Configuration.instance.equalSymbol)) {
                        try {
                            double iqr = Double.parseDouble(statisticMode.substring(Configuration.iqr.length() + Configuration.instance.equalSymbol.length()));
                            List<Double> iqrResult = StatisticsHelper.calculateIqr(evaluationDouble, iqr);
                            System.out.println(Configuration.iqr + ": " + iqrResult.get(0));
                            System.out.println("With range from " + iqrResult.get(1) + " to " + iqrResult.get(2));
                        } catch (Exception e) {
                            throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                        }
                    } else {
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    }
                    break;

                case Configuration.sd:
                    if (statisticMode.toLowerCase().equals(Configuration.sd))
                        System.out.println(Configuration.sd + ": " + StatisticsHelper.calculateSd(evaluationDouble));
                    else
                        throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
                    break;

                default:
                    throw new IllegalArgumentException(Configuration.instance.wrongStatisticMode + statisticMode);
            }
        }
    }

    private static void getDatabase(String database) {
        if (database.toLowerCase().equals(HSQLManager.tsp.toString().toLowerCase()))
            Statistics.database = HSQLManager.tsp;
        else if (database.toLowerCase().equals(HSQLManager.Knapsack.toString().toLowerCase()))
            Statistics.database = HSQLManager.Knapsack;
        else
            throw new IllegalArgumentException("No database with name: " + database + " found");
    }

    private static List<IEvaluation> getScenarioEvaluation(long scenarioID) {
        List<List<Object>> evaluations = Statistics.database.getAllEvaluationsForScenario(scenarioID);

        if (evaluations.size() == 0)
            throw new RuntimeException("ScenarioID not found");

        return EvaluationFactory.getMultipleInstancesFromRows(evaluations);
    }

    private static List<Integer> getListOfScenarios(String scenariosString) {
        if (scenariosString.equals(Configuration.instance.allScenarios)) {
            List<Integer> scenarios = new ArrayList<>();
            Statistics.database.getAllScenarios()
                    .forEach(list -> scenarios.add(Integer.parseInt(list.get(0).toString())));

            return scenarios;
        }

        String[] scenarioParsed = scenariosString.toLowerCase()
                .split(Configuration.instance.scenarioSeparator);

        List<Integer> returnList = new ArrayList<>();

        for (String scenario : scenarioParsed) {
            if (scenario.length() < 2 || scenario.charAt(0) != Configuration.instance.scenarioPrefix)
                throw new IllegalArgumentException("Please start a scenarioID with a 's'");

            scenario = scenario.substring(1);

            try {
                returnList.add(Integer.parseInt(scenario));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Wrong scenarioID: " + scenario);
            }
        }

        return returnList;
    }
}