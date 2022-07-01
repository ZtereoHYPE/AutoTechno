package codes.ztereohype.autotechno.chat;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MessageRandomiser {
    private static final Random RANDOM = new Random();
    private final Map<Event, List<String>> messagesMap = new HashMap<>();

    public MessageRandomiser(AutoTechnoConfig config) {
        messagesMap.put(Event.START_GAME, config.startMessageList);
        messagesMap.put(Event.END_GAME, config.endMessageList);
        messagesMap.put(Event.KILL, config.killMessageList);
    }

    public String getRandomMessage(Event event) {
        List<String> pickedList = messagesMap.get(event);
        int randomIndex = RANDOM.nextInt(pickedList.size()) - 1;

        return pickedList.get(randomIndex);
    }
}
