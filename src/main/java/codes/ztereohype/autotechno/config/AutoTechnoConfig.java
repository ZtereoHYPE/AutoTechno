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

    public static void init(int configVersion,
                            boolean sendEndMessages,
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
                if (getProperty("ConfigVersion") == null || Integer.parseInt((String) getProperty("ConfigVersion")) != configVersion) {
                    updateConfig(configVersion, sendEndMessages, sendStartMessages, sendKillMessages, messageWaitTime, endMessages, startMessages, killMessages);
                }
            } else {
                CONFIG_FILE.getParentFile().mkdirs();
                CONFIG_FILE.createNewFile();
                YamlWriter writer = new YamlWriter(new FileWriter(CONFIG_FILE));
                Map<String, Object> tempConfig = new LinkedHashMap<>();
                tempConfig.put("ConfigVersion", configVersion);
                tempConfig.put("SendEndMessages", sendEndMessages);
                tempConfig.put("SendStartMessages", sendStartMessages);
                tempConfig.put("SendKillMessages", sendKillMessages);
                tempConfig.put("MessageWaitTime", messageWaitTime);
                tempConfig.put("EndMessages", endMessages);
                tempConfig.put("StartMessages", startMessages);
                tempConfig.put("KillMessages", killMessages);
                writer.write(tempConfig);
                writer.close();
                init(configVersion, sendEndMessages, sendStartMessages, sendKillMessages, messageWaitTime, endMessages, startMessages, killMessages);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProperty(String property) {
        return config.get(property);
    }

    private static void updateConfig(int configVersion,
                                     boolean sendEndMessages,
                                     boolean sendStartMessages,
                                     boolean sendKillMessages,
                                     int messageWaitTime,
                                     String[] endMessages,
                                     String[] startMessages,
                                     String[] killMessages) {
        try {
            CONFIG_FILE.delete();
            CONFIG_FILE.createNewFile();
            YamlWriter writer = new YamlWriter(new FileWriter(CONFIG_FILE));
            Map<String, Object> tempConfig = new LinkedHashMap<>();
            tempConfig.put("ConfigVersion", configVersion);
            tempConfig.put("SendEndMessages", getProperty("SendEndMessages") == null ? sendEndMessages : getProperty("SendEndMessages"));
            tempConfig.put("SendStartMessages", getProperty("SendStartMessages") == null ? sendStartMessages : getProperty("SendEndMessages"));
            tempConfig.put("SendKillMessages", getProperty("SendKillMessages") == null ? sendKillMessages : getProperty("SendEndMessages"));
            tempConfig.put("MessageWaitTime", getProperty("MessageWaitTime") == null ? messageWaitTime : getProperty("MessageWaitTime"));
            tempConfig.put("EndMessages", getProperty("EndMessages") == null ? endMessages : getProperty("EndMessages"));
            tempConfig.put("StartMessages", getProperty("StartMessages") == null ? startMessages : getProperty("StartMessages"));
            tempConfig.put("KillMessages", getProperty("KillMessages") == null ? killMessages : getProperty("KillMessages"));
            writer.write(tempConfig);
            writer.close();
            init(configVersion, sendEndMessages, sendStartMessages, sendKillMessages, messageWaitTime, endMessages, startMessages, killMessages);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}