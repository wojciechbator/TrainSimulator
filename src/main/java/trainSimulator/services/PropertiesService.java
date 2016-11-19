package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
class PropertiesService {
    private static final Logger log = Logger.getLogger(PropertiesService.class);
    private String dbms;
    private String dbName;
    private String serverName;
    private String dbPass;
    private String dbUser;
    private String port;
    private static final String settingsFilePathProperty = "database.properties";

    String getDbms() {
        return dbms;
    }

    String getDbName() {
        return dbName;
    }

    String getServerName() {
        return serverName;
    }

    String getDbPass() {
        return dbPass;
    }

    String getDbUser() {
        return dbUser;
    }

    String getPort() {
        return port;
    }

    void readDBProperties() throws IOException {
        Properties properties = new Properties(System.getProperties());
        String databasePropertiesFile = System.getProperty(settingsFilePathProperty);
        if (databasePropertiesFile == null) {
            log.error("Settings path not set (use JVM property database.properties)");
        }
        FileInputStream propertyFile = null;
        if (databasePropertiesFile != null) {
            propertyFile = new FileInputStream(databasePropertiesFile);
        }
        properties.load(propertyFile);

        dbms = properties.getProperty("dbms");
        dbName = properties.getProperty("dbName");
        serverName = properties.getProperty("serverName");
        dbUser = properties.getProperty("dbUser");
        dbPass = properties.getProperty("dbPass");
        port = properties.getProperty("port");
    }
}
