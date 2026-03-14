package parser;

import java.io.File;

public class MissionParserFactory {

    public static MissionParser getParser(File file) throws MissionParseException {

        String name = file.getName().toLowerCase();

        if (name.endsWith(".txt")) {
            return new TxtMissionParser();
        }
        if (name.endsWith(".json")) {
            return new JsonMissionParser();
        }
        if (name.endsWith(".xml")) {
            return new XmlMissionParser();
        }

        throw new MissionParseException("Неподдерживаемый формат файла: " + name +
                "\nПоддерживаются только .txt, .json, .xml");
    }

    public static MissionParser getParser(String filePath) throws MissionParseException {
        return getParser(new File(filePath));
    }
}
