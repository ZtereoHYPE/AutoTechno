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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AutoTechnoConfig {
    public static final Path CONFIG_PATH = Paths.get("config/autogg.json");
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

    public boolean sendEndMessages;
    public boolean sendStartMessages;
    public boolean sendKillMessages;

    public List<String> endMessageList;
    public List<String> startMessageList;
    public List<String> killMessageList;

    private AutoTechnoConfig(boolean sendEndMessages,
                             boolean sendStartMessages,
                             boolean sendKillMessages,
                             String[] endMessageList,
                             String[] startMessageList,
                             String[] killMessageList) {
        this.sendEndMessages = sendEndMessages;
        this.sendStartMessages = sendStartMessages;
        this.sendKillMessages = sendKillMessages;
        this.endMessageList = Arrays.asList(endMessageList);
        this.startMessageList = Arrays.asList(startMessageList);
        this.killMessageList = Arrays.asList(endMessageList);
    }

    public static AutoTechnoConfig get(boolean endMessages,
                                       boolean startMessages,
                                       boolean finalMessages,
                                       String[] endMessageList,
                                       String[] startMessageList,
                                       String[] killMessageList) {
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
