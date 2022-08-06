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

    private static final Map<String, Object> DEFAULT_CONFIG = new LinkedHashMap<>() {{
        put("SendEndMessages", true);
        put("SendStartMessages", true);
        put("SendKillMessages", true);
        put("MessageWaitTime", 3000);
        put("EndMessages", new String[]{"gg e z",
                "good game",
                "Rest in Peace Technoblade",
                "Technoblade never dies",
                "so long nerds",
                "as Sun Tzu wanted"});
        put("StartMessages", new String[]{"Good luck, and may Techno's unmatched skill be with you",
                "RIP Techno, you will be missed.",
                "Let's drop kick some children!",
                "Technoblade never dies!",
                "So, what do you guys know about anarchy?"});
        put("KillMessages", new String[]{"Blood for the Blood God",
                "In the name of techno",
                "This ones for technoblade",
                "Officer, I drop-kicked them in self defense!",
                "This is the second-worst thing to happen to these orphans.",
                "Sometimes it's tough being the best",
                "die nerd (/j)",
                "chin up king, your crown is falling"});
    }};

    private static Map<String, Object> CONFIG = new LinkedHashMap<>();

    public static void init() {
        try {
            if (CONFIG_FILE.exists()) {
                YamlReader reader = new YamlReader(new FileReader(CONFIG_FILE));

                CONFIG = (Map<String, Object>) reader.read();
                reader.close();

                if (!CONFIG.keySet().equals(DEFAULT_CONFIG.keySet())) {
                    updateConfig();
                }
            } else {
                CONFIG_FILE.getParentFile().mkdirs();
                CONFIG_FILE.createNewFile();

                YamlWriter writer = new YamlWriter(new FileWriter(CONFIG_FILE));

                writer.write(DEFAULT_CONFIG);
                writer.close();

                init();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getProperty(String property) {
        return CONFIG.get(property);
    }

    private static void updateConfig() {
        try {
            CONFIG_FILE.delete();
            CONFIG_FILE.createNewFile();

            YamlWriter writer = new YamlWriter(new FileWriter(CONFIG_FILE));

            Map<String, Object> updatedConfig = new LinkedHashMap<>();

            for (String key : DEFAULT_CONFIG.keySet()) {
                Object property = getProperty(key);
                updatedConfig.put(key, property == null ? DEFAULT_CONFIG.get(key) : property);
            }

            writer.write(updatedConfig);
            writer.close();

            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}