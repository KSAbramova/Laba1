package parser;

import model.*;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import javax.xml.stream.*;

public class XmlMissionParser implements MissionParser {

    @Override
    public Mission parse(File file) throws MissionParseException {
        Mission mission = new Mission();
        XMLStreamReader reader = null;

        try (FileInputStream fis = new FileInputStream(file)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            reader = factory.createXMLStreamReader(fis);

            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    String tag = reader.getLocalName();

                    switch (tag) {
                        case "missionId"    -> mission.setMissionId(reader.getElementText());
                        case "date"         -> mission.setDate(LocalDate.parse(reader.getElementText()));
                        case "location"     -> mission.setLocation(reader.getElementText());
                        case "outcome"      -> mission.setOutcome(Mission.Outcome.valueOf(reader.getElementText()));
                        case "damageCost"   -> mission.setDamageCost(Long.parseLong(reader.getElementText()));
                        case "comment"      -> mission.setNote(reader.getElementText());

                        case "curse"        -> parseCurse(reader, mission);
                        case "sorcerer"     -> parseSorcerer(reader, mission);
                        case "technique"    -> parseTechnique(reader, mission);
                    }
                }
            }
        } catch (Exception e) {
            throw new MissionParseException("Ошибка при разборе XML-файла: " + file.getName());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException ignored) {
                }
            }
        }
        return mission;
    }

    private void parseCurse(XMLStreamReader reader, Mission mission) throws XMLStreamException {
        Curse curse = new Curse();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && "curse".equals(reader.getLocalName())) break;

            if (event == XMLStreamConstants.START_ELEMENT) {
                String tag = reader.getLocalName();
                if ("name".equals(tag)) curse.setName(reader.getElementText());
                if ("threatLevel".equals(tag)) curse.setThreatLevel(Curse.ThreatLevel.valueOf(reader.getElementText()));
            }
        }
        mission.setCurse(curse);
    }

    private void parseSorcerer(XMLStreamReader reader, Mission mission) throws XMLStreamException {
        Sorcerer s = new Sorcerer();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && "sorcerer".equals(reader.getLocalName())) break;

            if (event == XMLStreamConstants.START_ELEMENT) {
                String tag = reader.getLocalName();
                if ("name".equals(tag)) s.setName(reader.getElementText());
                if ("rank".equals(tag)) s.setRank(Sorcerer.Rank.valueOf(reader.getElementText()));
            }
        }
        mission.addSorcerer(s);
    }

    private void parseTechnique(XMLStreamReader reader, Mission mission) throws XMLStreamException {
        Technique t = new Technique();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.END_ELEMENT && "technique".equals(reader.getLocalName())) break;

            if (event == XMLStreamConstants.START_ELEMENT) {
                String tag = reader.getLocalName();
                if ("name".equals(tag)) t.setName(reader.getElementText());
                if ("type".equals(tag)) t.setType(Technique.Type.valueOf(reader.getElementText()));
                if ("owner".equals(tag)) t.setOwner(reader.getElementText());
                if ("damage".equals(tag)) t.setDamage(Long.parseLong(reader.getElementText()));
            }
        }
        mission.addTechnique(t);
    }
}