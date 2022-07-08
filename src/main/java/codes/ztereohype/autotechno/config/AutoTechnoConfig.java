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
                                       int messageWaitTime,
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
                Map<String, Object> tempConfig = new LinkedHashMap<>();
                tempConfig.put("SendEndMessages", sendEndMessages);
                tempConfig.put("SendStartMessages", sendStartMessages);
                tempConfig.put("SendKillMessages", sendKillMessages);
                tempConfig.put("MessageWaitTime", messageWaitTime);
                tempConfig.put("EndMessages", endMessages);
                tempConfig.put("StartMessages", startMessages);
                tempConfig.put("KillMessages", killMessages);
                writer.write(tempConfig);
                writer.close();
                init(sendEndMessages, sendStartMessages, sendKillMessages, messageWaitTime, endMessages, startMessages, killMessages);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProperty(String property) {
        return config.get(property);
    }
}