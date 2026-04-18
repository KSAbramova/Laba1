package parser;

import model.Mission;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import parser.handlers.*;

public class MissionParserChain {

    private static final List<MissionParser> PARSERS;

    static {
        PARSERS = new ArrayList<>();
        PARSERS.add(new TxtMissionParserHandler());
        PARSERS.add(new NoExtensionMissionParserHandler());
        PARSERS.add(new JsonMissionParserHandler());
        PARSERS.add(new XmlMissionParserHandler());
        PARSERS.add(new YamlMissionParserHandler());
    }

    public static MissionParser getChain() {
        if (PARSERS.isEmpty()) {
            return null;
        }

        for (int i = 0; i < PARSERS.size() - 1; i++) {
            PARSERS.get(i).setNext(PARSERS.get(i + 1));
        }

        return PARSERS.get(0);
    }

    public static Mission parse(File file) throws MissionParseException {
        MissionParser chain = getChain();
        if (chain == null) {
            throw new MissionParseException("Нет ни одного парсера в цепочке");
        }
        return chain.parse(file);
    }
}