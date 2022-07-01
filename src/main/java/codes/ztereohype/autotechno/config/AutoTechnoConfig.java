package codes.ztereohype.autotechno.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoTechnoConfig {
    public static final Path CONFIG_PATH = Paths.get("config/autogg.json");
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    public boolean endMessages;
    public boolean startMessages;
    public boolean killMessages;

    public List<String> endMessageList = new ArrayList<>();
    public List<String> startMessageList = new ArrayList<>();
    public List<String> killMessageList = new ArrayList<>();

    private AutoTechnoConfig(boolean endMessages, boolean startMessages, boolean killMessages, List<String> endMessageList, List<String> startMessageList, List<String> killMessageList) {
        this.endMessages = endMessages;
        this.startMessages = startMessages;
        this.killMessages = killMessages;
        this.endMessageList = endMessageList;
        this.startMessageList = startMessageList;
        this.killMessageList = killMessageList;
    }

    public static AutoTechnoConfig get(boolean endMessages, boolean startMessages, boolean finalMessages, List<String> endMessageList, List<String> startMessageList, List<String> killMessageList) {
        try {
            if (CONFIG_PATH.toFile().exists()) {
                return GSON.fromJson(new String(Files.readAllBytes(CONFIG_PATH)), AutoTechnoConfig.class);
            } else {
                AutoTechnoConfig config = new AutoTechnoConfig(endMessages, startMessages, finalMessages, endMessageList, startMessageList, killMessageList);
                new File(FilenameUtils.getPath(CONFIG_PATH.toString())).mkdir();
                Files.write(CONFIG_PATH, Collections.singleton(GSON.toJson(config)));
                return config;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
