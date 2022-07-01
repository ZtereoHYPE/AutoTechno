package codes.ztereohype.autotechno.chat;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.client.KnownServer;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class MessageDetector {
    private static final long WAIT_TIME = 3000L;

    private long lastTime = 0;
    private final MinecraftClient client = MinecraftClient.getInstance();

    private final EnumMap<MessageEvent, List<String>> eventMessages = new EnumMap<>(MessageEvent.class);

    private final List<String> hypixelEndStrings = new ArrayList<>();
    private final List<String> bedwarsPracticeEndStrings = new ArrayList<>();
    private final List<String> pvpLandEndStrings = new ArrayList<>();
    private final List<String> minemenEndStrings = new ArrayList<>();

    private final List<String> hypixelStartStrings = new ArrayList<>();
    private final List<String> bedwarsPracticeStartStrings = new ArrayList<>();
    private final List<String> pvpLandStartStrings = new ArrayList<>();
    private final List<String> minemenStartStrings = new ArrayList<>();

    private final List<String> hypixelFinalStrings = new ArrayList<>();
    private final List<String> bedwarsPracticeFinalStrings = new ArrayList<>();
    private final List<String> pvpLandFinalStrings = new ArrayList<>();
    private final List<String> minemenFinalStrings = new ArrayList<>();

    private void populateHypixelEndStrings()
    {   hypixelEndStrings.add("1st Killer -");
        hypixelEndStrings.add("1st Place -");
        hypixelEndStrings.add("Winner:");
        hypixelEndStrings.add(" - Damage Dealt -");
        hypixelEndStrings.add("Winning Team -");
        hypixelEndStrings.add("1st -");
        hypixelEndStrings.add("Winners:");
        hypixelEndStrings.add("Winner:");
        hypixelEndStrings.add("Winning Team:");
        hypixelEndStrings.add(" won the game!");
        hypixelEndStrings.add("Top Seeker:");
        hypixelEndStrings.add("1st Place:");
        hypixelEndStrings.add("Last team standing!");
        hypixelEndStrings.add("Winner #1 (");
        hypixelEndStrings.add("Top Survivors");
        hypixelEndStrings.add("Winners -");
        hypixelEndStrings.add("Sumo Duel -");
        hypixelEndStrings.add("Most Wool Placed -");
        hypixelEndStrings.add("Your Overall Winstreak:");
    }
    private void populateBedwarsPracticeEndStrings() {
        bedwarsPracticeEndStrings.add("Winners -");
        bedwarsPracticeEndStrings.add("Game Won!");
        bedwarsPracticeEndStrings.add("Game Lost!");
        bedwarsPracticeEndStrings.add("The winning team is");
    }
    private void populatePvpLandEndStrings() {
        pvpLandEndStrings.add("The match has ended!");
        pvpLandEndStrings.add("Match Results");
        pvpLandEndStrings.add("Winner:");
        pvpLandEndStrings.add("Loser:");
    }
    private void populateMinemenEndStrings() {
        minemenEndStrings.add("Match Results");
    }

    private void populateHypixelStartStrings() {
        hypixelStartStrings.add("The game starts in 1 second!");
    }
    private void populateBedwarsPracticeStartStrings() {
        bedwarsPracticeStartStrings.add("Game starting in 1 seconds!");
        bedwarsPracticeStartStrings.add("Game has started!");
    }
    private void populatePvpLandStartStrings() {
        pvpLandStartStrings.add("The match is starting in 1 second.");
        pvpLandStartStrings.add("The match has started!");
    }
    private void populateMinemenStartStrings() {
        minemenStartStrings.add("1...");
    }

    private void populateHypixelFinalStrings() {
        hypixelFinalStrings.add("SkyWars Experience (Kill)");
        hypixelFinalStrings.add("coins! (Final Kill)");
    }
    private void populateBedwarsPracticeFinalStrings() {
        bedwarsPracticeFinalStrings.add(client.getSession().getUsername() + " FINAL KILL!");
    }
    private void populatePvpLandFinalStrings() {
        pvpLandFinalStrings.add("slain by " + client.getSession().getUsername());
    }
    private void populateMinemenFinalStrings() {
        minemenFinalStrings.add("killed by " + client.getSession().getUsername() + "!");
    }

    private final boolean finalMessages;
    private final boolean startMessages;
    private final boolean endMessages;

    public MessageDetector(AutoTechnoConfig config) {
        this.endMessages = config.endMessages;
        this.finalMessages = config.finalMessages;
        this.startMessages = config.startMessages;
    }

    public @Nullable MessageEvent scanForEvent() {
        if (System.currentTimeMillis() - this.lastTime <= 3000) return null;

        KnownServer server = AutoTechno.client.getCurrentServer();


//            if (messageRecieved.getString().contains(s)) {x
//                client.player.sendChatMessage(messageToSend);
//                this.lastTime = System.currentTimeMillis();
//                return;
//            }
        }
    }

//    public String getRandomMessage(MessageCategory cat) {
//        if (cat == MessageCategory.END_GAME) {
//            retu
//        }
//    }
}
