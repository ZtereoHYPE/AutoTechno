package codes.ztereohype.autotechno;

import codes.ztereohype.autotechno.chat.MessageDetector;
import codes.ztereohype.autotechno.client.ClientWrapper;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import net.fabricmc.api.ModInitializer;

public class AutoTechno implements ModInitializer {
    public static AutoTechnoConfig config = AutoTechnoConfig.get(true, true, true);
    public static ClientWrapper client;
    public static MessageDetector detector;

    @Override
    public void onInitialize() {
        client = new ClientWrapper();
        detector = new MessageDetector(config);
    }
}
