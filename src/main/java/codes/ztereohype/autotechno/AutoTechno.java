package codes.ztereohype.autotechno;

import codes.ztereohype.autotechno.chat.EventDetector;
import codes.ztereohype.autotechno.client.ClientWrapper;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.fabricmc.api.ModInitializer;

public class AutoTechno implements ModInitializer {
    public static AutoTechnoConfig config;
    static {
        String[] startMessages = new String[] {"Good luck, and may Techno's unmatched skill be with you",
                                              "RIP Techno, you will be missed.",
                                              "Let's drop kick some children!"};
        String[] killMessages = new String[] {"Blood for the Blood God",
                                              "In the name of techno",
                                              "This ones for technoblade",
                                              "Officer, I drop-kicked them in self defense!"};
        String[] endMessages = new String[] {"gg ez",
                                             "good game",
                                             "Rest in Peace Technoblade"};

        config = AutoTechnoConfig.get(true, true, true, startMessages, killMessages, endMessages);
    }
    public static ClientWrapper client;
    public static EventDetector detector;

    @Override
    public void onInitialize() {
        client = new ClientWrapper();
        detector = new EventDetector(config);
    }
}
