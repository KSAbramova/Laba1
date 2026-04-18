package gui.reports;

import java.util.*;

public class ReportStrategyRegistry {

    private static final Map<String, MissionReportStrategy> strategies = new LinkedHashMap<>();

    static {
        register(new DefaultMissionReportStrategy());
        register(new DetailedMissionReportStrategy());
    }

    public static void register(MissionReportStrategy strategy) {
        strategies.put(strategy.getName(), strategy);
    }

    public static MissionReportStrategy getStrategyByName(String name) {
        MissionReportStrategy strategy = strategies.get(name);
        if (strategy == null) {
            throw new IllegalArgumentException("Неизвестный тип отчёта: " + name);
        }
        return strategy;
    }

    public static List<String> getStrategyNames() {
        return new ArrayList<>(strategies.keySet());
    }
}