package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Mission;
import java.io.File;

public class JsonMissionParser implements MissionParser {

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public Mission parse(File file) throws MissionParseException {
        try {
            return mapper.readValue(file, Mission.class);
        } catch (Exception e) {
            throw new MissionParseException("Ошибка при разборе JSON-файла: " + file.getName(), e);
        }
    }
}