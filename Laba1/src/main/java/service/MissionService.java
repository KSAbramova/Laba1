package service;

import gui.reports.MissionReportStrategy;
import gui.reports.ReportStrategyRegistry;
import model.Mission;
import parser.MissionParseException;
import parser.MissionParserChain;

import java.io.File;

public class MissionService implements FacadeMissionService {

    @Override
    public Mission parse(File file) throws MissionParseException {
        return MissionParserChain.parse(file);
    }

    @Override
    public String generateReport(Mission mission, String reportType) {
        if (mission == null) return "";
        MissionReportStrategy strategy = ReportStrategyRegistry.getStrategyByName(reportType);
        return strategy.generate(mission);
    }
}