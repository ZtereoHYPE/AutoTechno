package codes.ztereohype.autotechno.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class AutoTechnoConfig {
    private static final File CONFIG_FILE = Paths.get("config/autotechno.yml").toFile();
    private static Map<String, Object> config = new LinkedHashMap<>();

    public static void init(boolean sendEndMessages,
                                       boolean sendStartMessages,
                                       boolean sendKillMessages,
                                       String[] endMessages,
                                       String[] startMessages,
                                       String[] killMessages) {
        try {
            if (CONFIG_FILE.exists()) {
                YamlReader reader = new YamlReader(new FileReader(CONFIG_FILE));
                config = (Map<String, Object>) reader.read();
                reader.close();
            } else {
                CONFIG_FILE.getParentFile().mkdirs();
                CONFIG_FILE.createNewFile();
                YamlWriter writer = new YamlWriter(new FileWriter(CONFIG_FILE));
                config.put("SendEndMessages", sendEndMessages);
                config.put("SendStartMessages", sendStartMessages);
                config.put("SendKillMessages", sendKillMessages);
                config.put("EndMessages", endMessages);
                config.put("StartMessages", startMessages);
                config.put("KillMessages", killMessages);
                writer.write(config);
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProperty(String property) {
        return config.get(property);
    }
}