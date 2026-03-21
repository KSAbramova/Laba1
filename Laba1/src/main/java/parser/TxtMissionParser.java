package parser;

import model.*;
import java.io.*;
import java.time.LocalDate;

public class TxtMissionParser implements MissionParser {

    @Override
    public Mission parse(File file) throws MissionParseException {
        Mission mission = new Mission();
        mission.setCurse(new Curse());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split(":", 2);
                if (parts.length < 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                parseKeyValue(mission, key, value);
            }
        } catch (Exception e) {
            throw new MissionParseException("Ошибка при разборе TXT-файла: " + file.getName(), e);
        }
        return mission;
    }

    private void parseKeyValue(Mission mission, String key, String value) {
        switch (key) {
            case "missionId"    -> mission.setMissionId(value);
            case "date"         -> mission.setDate(LocalDate.parse(value));
            case "location"     -> mission.setLocation(value);
            case "outcome"      -> mission.setOutcome(Mission.Outcome.valueOf(value));
            case "damageCost"   -> mission.setDamageCost(Long.parseLong(value));
            case "note"         -> mission.setNote(value);
            case "curse.name"        -> mission.getCurse().setName(value);
            case "curse.threatLevel" -> mission.getCurse().setThreatLevel(Curse.ThreatLevel.valueOf(value));

            case String k when k.startsWith("sorcerer[") && k.endsWith("].name") -> {
                Sorcerer s = new Sorcerer();
                s.setName(value);
                mission.addSorcerer(s);
            }
            case String k when k.startsWith("sorcerer[") && k.endsWith("].rank") -> {
                int i = mission.getSorcerersSize() - 1;
                if (i >= 0) mission.getSorcerers().get(i).setRank(Sorcerer.Rank.valueOf(value));
            }

            case String k when k.startsWith("technique[") && k.endsWith("].name") -> {
                Technique t = new Technique();
                t.setName(value);
                mission.addTechnique(t);
            }
            case String k when k.startsWith("technique[") && k.endsWith("].type") -> {
                int i = mission.geTechniqueSize() - 1;
                if (i >= 0) mission.getTechniques().get(i).setType(Technique.Type.valueOf(value));
            }
            case String k when k.startsWith("technique[") && k.endsWith("].owner") -> {
                int i = mission.geTechniqueSize() - 1;
                if (i >= 0) mission.getTechniques().get(i).setOwner(value);
            }
            case String k when k.startsWith("technique[") && k.endsWith("].damage") -> {
                int i = mission.geTechniqueSize() - 1;
                if (i >= 0) mission.getTechniques().get(i).setDamage(Long.parseLong(value));
            }

            default -> {
            }
        }
    }
}