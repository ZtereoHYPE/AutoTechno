package codes.ztereohype.autotechno.chat;

import codes.ztereohype.autotechno.config.AutoTechnoConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MessageRandomiser {
    private static final Random RANDOM = new Random();
    private final Map<Event, List<String>> messagesMap = new HashMap<>();

    public MessageRandomiser() {
        messagesMap.put(Event.START_GAME, (List<String>) AutoTechnoConfig.getProperty("StartMessages"));
        messagesMap.put(Event.END_GAME, (List<String>) AutoTechnoConfig.getProperty("EndMessages"));
        messagesMap.put(Event.KILL, (List<String>) AutoTechnoConfig.getProperty("KillMessages"));
    }

    public String getRandomMessage(Event event) {
        List<String> pickedList = messagesMap.get(event);
        int randomIndex = RANDOM.nextInt(pickedList.size());

        return pickedList.get(randomIndex);
    }
}