package codes.ztereohype.autotechno;

import codes.ztereohype.autotechno.chat.EventDetector;
import codes.ztereohype.autotechno.client.ClientWrapper;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.fabricmc.api.ModInitializer;

public class AutoTechno implements ModInitializer {
    public static AutoTechnoConfig config = AutoTechnoConfig.get(true, true, true);
    public static ClientWrapper client;
    public static EventDetector detector;

    @Override
    public void onInitialize() {
        client = new ClientWrapper();
        detector = new EventDetector(config);
    }
}
