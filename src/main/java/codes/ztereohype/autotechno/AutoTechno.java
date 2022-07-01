package codes.ztereohype.autotechno;

import codes.ztereohype.autotechno.chat.EventDetector;
import codes.ztereohype.autotechno.client.ClientWrapper;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.fabricmc.api.ModInitializer;

import java.util.ArrayList;
import java.util.List;

public class AutoTechno implements ModInitializer {
    public static AutoTechnoConfig config;
    static {
        List<String> endMessageList = new ArrayList<String>() {{
            add("");
        }};
        List<String> startMessageList = new ArrayList<String>() {{
            add("");
        }};
        List<String> killMessageList = new ArrayList<String() {{
            add("");
        }};
        config = AutoTechnoConfig.get(true, true, true, endMessageList, startMessageList, killMessageList);
    }
    public static ClientWrapper client;
    public static EventDetector detector;

    @Override
    public void onInitialize() {
        client = new ClientWrapper();
        detector = new EventDetector(config);
    }
}
