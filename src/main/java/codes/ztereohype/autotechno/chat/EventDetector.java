package codes.ztereohype.autotechno.chat;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.client.Server;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class EventDetector {
    public static boolean mineplexStart = false;
    private final Map<Server, Map<String, Event>> serverMessageEvents = new HashMap<>() {{
        put(Server.HYPIXEL, new HashMap<>());
        put(Server.BEDWARS_PRACTICE, new HashMap<>());
        put(Server.PVPLAND, new HashMap<>());
        put(Server.MINEMEN, new HashMap<>());
        put(Server.MINEPLEX, new HashMap<>());
    }};
    private final boolean killMessages;
    private final boolean startMessages;
    private final boolean endMessages;

    public EventDetector(AutoTechnoConfig config) {
        this.endMessages = config.sendEndMessages;
        this.killMessages = config.sendKillMessages;
        this.startMessages = config.sendStartMessages;
        initMessages();
    }

    private void initMessages() {
        // END STRINGS
        serverMessageEvents.get(Server.HYPIXEL).put("Your Overall Winstreak:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("1st Place -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("1st Killer -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put(" - Damage Dealt -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winning Team -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("1st -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winners:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winner:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winning Team:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put(" won the game!", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Top Seeker:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("1st Place:", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Last team standing!", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winner #1 (", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Top Survivors", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Winners -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Sumo Duel -", Event.END_GAME);
        serverMessageEvents.get(Server.HYPIXEL).put("Most Wool Placed -", Event.END_GAME);

        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("Winners -", Event.END_GAME);
        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("Game Won!", Event.END_GAME);
        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("Game Lost!", Event.END_GAME);
        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("The winning team is", Event.END_GAME);

        serverMessageEvents.get(Server.PVPLAND).put("The match has ended!", Event.END_GAME);
        serverMessageEvents.get(Server.PVPLAND).put("Match Results", Event.END_GAME);
        serverMessageEvents.get(Server.PVPLAND).put("Winner:", Event.END_GAME);
        serverMessageEvents.get(Server.PVPLAND).put("Loser:", Event.END_GAME);

        serverMessageEvents.get(Server.MINEMEN).put("Match Results", Event.END_GAME);

        serverMessageEvents.get(Server.MINEPLEX).put("Chat> Chat is no longer silenced.", Event.END_GAME);

        // START STRINGS
        serverMessageEvents.get(Server.HYPIXEL).put("The game starts in 1 second!", Event.START_GAME);

        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("Game starting in 1 seconds!", Event.START_GAME);
        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put("Game has started!", Event.START_GAME);

        serverMessageEvents.get(Server.PVPLAND).put("The match is starting in 1 second.", Event.START_GAME);
        serverMessageEvents.get(Server.PVPLAND).put("The match has started!", Event.START_GAME);

        serverMessageEvents.get(Server.MINEMEN).put("1...", Event.START_GAME);

        // KILL STRINGS
        serverMessageEvents.get(Server.HYPIXEL).put("SkyWars Experience (Kill)", Event.KILL);
        serverMessageEvents.get(Server.HYPIXEL).put("coins! (Final Kill)", Event.KILL);

        serverMessageEvents.get(Server.BEDWARS_PRACTICE).put(AutoTechno.client.getClient().getSession().getUsername() + " FINAL KILL!", Event.KILL);

        serverMessageEvents.get(Server.PVPLAND).put("slain by " + AutoTechno.client.getClient().getSession().getUsername(), Event.KILL);

        serverMessageEvents.get(Server.MINEMEN).put("killed by " + AutoTechno.client.getClient().getSession().getUsername() + "!", Event.KILL);

        serverMessageEvents.get(Server.MINEPLEX).put("Death> You killed", Event.KILL);
    }

    public @Nullable Event scanForEvent(@NotNull String message) {
        Server server = AutoTechno.client.getCurrentServer();
        if (server == null) return null;

        if (server == Server.MINEPLEX && message.contains("You have been sent from ") && message.contains(" to Lobby")) {
            mineplexStart = false;
        }

        for (String s : serverMessageEvents.get(server).keySet()) {
            if (message.contains(s)) {
                Event event = serverMessageEvents.get(server).get(s);

                if (server == Server.MINEPLEX && event == Event.END_GAME) {
                    mineplexStart = !mineplexStart;
                    return mineplexStart ? Event.START_GAME : Event.END_GAME;
                }

                switch (event) {
                    case KILL:
                        if (this.killMessages) return event;
                        break;
                    case START_GAME:
                        if (this.startMessages) return event;
                        break;
                    case END_GAME:
                        if (this.endMessages) return event;
                        break;
                }
            }
        }
        return null;
    }
}