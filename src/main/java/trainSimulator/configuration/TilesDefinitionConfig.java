package trainSimulator.configuration;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

public final class TilesDefinitionConfig implements DefinitionsFactory {

    private static final Map<String, Definition> TILES_DEFINITIONS = new HashMap<>();

    @Override
    public Definition getDefinition(String name, Request rqst) {

        return TILES_DEFINITIONS.get(name);
    }

    public static void addDefinitions() {

        addDefaultLayoutDefinition("index", "Start", "/WEB-INF/views/index.jsp");
        addDefaultLayoutDefinition("trains", "List of trains", "/WEB-INF/views/trains.jsp");

    }

    private static void addDefaultLayoutDefinition(String name, String title, String body) {

        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/basicLayout/commonHeader.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/basicLayout/commonFooter.jsp"));

        Attribute baseTemplate = new Attribute("/WEB-INF/basicLayout/commonLayout.jsp");

        TILES_DEFINITIONS.put(name, new Definition(name, baseTemplate, attributes));
    }
}
