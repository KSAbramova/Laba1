package service;

import model.Mission;
import parser.MissionParseException;

import java.io.File;

public interface FacadeMissionService {

    Mission parse(File file) throws MissionParseException;
    String generateReport(Mission mission, String reportType);
}