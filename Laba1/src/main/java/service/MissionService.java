package service;

import java.io.File;
import model.Mission;
import parser.MissionParseException;
import parser.MissionParser;
import parser.MissionParserFactory;

public class MissionService {

    public Mission loadMission(File file) throws MissionParseException {
        if (file == null || !file.exists()) {
            throw new MissionParseException("Файл не существует или не указан");
        }

        MissionParser parser = MissionParserFactory.getParser(file);
        return parser.parse(file);
    }

    public Mission loadMission(String filePath) throws MissionParseException {
        return loadMission(new File(filePath));
    }

}
