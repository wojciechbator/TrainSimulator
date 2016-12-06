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
    public Definition getDefinition(String name, Request request) {

        return TILES_DEFINITIONS.get(name);
    }

    public static void addDefinitions() {

        addDefaultLayoutDefinition("index", "Start", "/WEB-INF/views/index.jsp", "index");
        addDefaultLayoutDefinition("timetable", "Displayed Timetable", "/WEB-INF/views/timetable.jsp", "timetable");
        addDefaultLayoutDefinition("adminPanel", "Admin Panel", "/WEB-INF/views/adminPanel.jsp", "adminPanel");
//        addDefaultLayoutDefinition("simulation", "Simulation", "/WEB-INF/views/someView.jsp", "simulation");

    }

    private static void addDefaultLayoutDefinition(String name, String title, String body, String current) {

        Map<String, Attribute> attributes = new HashMap<>();

        attributes.put("title", new Attribute(title));
        attributes.put("header", new Attribute("/WEB-INF/common/commonHeader.jsp"));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute("/WEB-INF/common/commonFooter.jsp"));
        attributes.put("current", new Attribute(current));

        Attribute baseTemplate = new Attribute("/WEB-INF/common/commonLayout.jsp");

        TILES_DEFINITIONS.put(name, new Definition(name, baseTemplate, attributes));
    }
}
