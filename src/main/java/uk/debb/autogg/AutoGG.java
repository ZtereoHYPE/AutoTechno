package uk.debb.autogg;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import net.fabricmc.api.ModInitializer;

public class AutoGG implements ModInitializer {
    public static AutoGGConfig config = new AutoGGConfig(true, true, true);
    public Gson data = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
    Path configPath = Paths.get("config/autogg.json");
    static AutoGG autoGGStatic;

    public static void saveDataToFileStatic() {
        autoGGStatic.saveDataToFile();
    }

    public void saveDataToFile() {
        if (config != null) {
            try {
                Files.write(configPath, Collections.singleton(data.toJson(config)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        try {
            if (configPath.toFile().exists()) {
                config = data.fromJson(new String(Files.readAllBytes(configPath)), AutoGGConfig.class);
            } else {
                Files.write(configPath, Collections.singleton(data.toJson(config)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInitialize() {
        autoGGStatic = this;
        saveData();
    }
}
