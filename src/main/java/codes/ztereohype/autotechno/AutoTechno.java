package codes.ztereohype.autotechno;

import codes.ztereohype.autotechno.chat.EventDetector;
import codes.ztereohype.autotechno.chat.MessageRandomiser;
import codes.ztereohype.autotechno.client.ClientWrapper;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.fabricmc.api.ModInitializer;

public class AutoTechno implements ModInitializer {
    public static ClientWrapper client;
    public static EventDetector detector;
    public static MessageRandomiser messageRandomiser;
    public static int CONFIG_VERSION = 1;

    @Override
    public void onInitialize() {
        AutoTechnoConfig.init();

        client = new ClientWrapper();
        detector = new EventDetector();
        messageRandomiser = new MessageRandomiser();
    }
}