package codes.ztereohype.autotechno.mixin;

import java.util.ArrayList;
import java.util.List;

import codes.ztereohype.autotechno.AutoTechno;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class MixinChatHud {
    @Unique private long lastTime = 0;

    @Shadow @Final private MinecraftClient client;

    @Unique private final List<String> hypixelEndStrings = new ArrayList<>();
    @Unique private final List<String> bedwarsPracticeEndStrings = new ArrayList<>();
    @Unique private final List<String> pvpLandEndStrings = new ArrayList<>();
    @Unique private final List<String> minemenEndStrings = new ArrayList<>();

    @Unique private final List<String> hypixelStartStrings = new ArrayList<>();
    @Unique private final List<String> bedwarsPracticeStartStrings = new ArrayList<>();
    @Unique private final List<String> pvpLandStartStrings = new ArrayList<>();
    @Unique private final List<String> minemenStartStrings = new ArrayList<>();

    @Unique private final List<String> hypixelFinalStrings = new ArrayList<>();
    @Unique private final List<String> bedwarsPracticeFinalStrings = new ArrayList<>();
    @Unique private final List<String> pvpLandFinalStrings = new ArrayList<>();
    @Unique private final List<String> minemenFinalStrings = new ArrayList<>();

    @Unique private void populateHypixelEndStrings()
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
    @Unique private void populateBedwarsPracticeEndStrings() {
        bedwarsPracticeEndStrings.add("Winners -");
        bedwarsPracticeEndStrings.add("Game Won!");
        bedwarsPracticeEndStrings.add("Game Lost!");
        bedwarsPracticeEndStrings.add("The winning team is");
    }
    @Unique private void populatePvpLandEndStrings() {
        pvpLandEndStrings.add("The match has ended!");
        pvpLandEndStrings.add("Match Results");
        pvpLandEndStrings.add("Winner:");
        pvpLandEndStrings.add("Loser:");
    }
    @Unique private void populateMinemenEndStrings() {
        minemenEndStrings.add("Match Results");
    }

    @Unique private void populateHypixelStartStrings() {
        hypixelStartStrings.add("The game starts in 1 second!");
    }
    @Unique private void populateBedwarsPracticeStartStrings() {
        bedwarsPracticeStartStrings.add("Game starting in 1 seconds!");
        bedwarsPracticeStartStrings.add("Game has started!");
    }
    @Unique private void populatePvpLandStartStrings() {
        pvpLandStartStrings.add("The match is starting in 1 second.");
        pvpLandStartStrings.add("The match has started!");
    }
    @Unique private void populateMinemenStartStrings() {
        minemenStartStrings.add("1...");
    }

    @Unique private void populateHypixelFinalStrings() {
        hypixelFinalStrings.add("SkyWars Experience (Kill)");
        hypixelFinalStrings.add("coins! (Final Kill)");
    }
    @Unique private void populateBedwarsPracticeFinalStrings() {
        bedwarsPracticeFinalStrings.add(client.getSession().getUsername() + " FINAL KILL!");
    }
    @Unique private void populatePvpLandFinalStrings() {
        pvpLandFinalStrings.add("slain by " + client.getSession().getUsername());
    }
    @Unique private void populateMinemenFinalStrings() {
        minemenFinalStrings.add("killed by " + client.getSession().getUsername() + "!");
    }

    @Unique private void processChat(Text messageRecieved, List<String> options, String messageToSend) {
        if (System.currentTimeMillis() - this.lastTime <= 3000) return;
        for (String s : options) {
            if (messageRecieved.getString().contains(s)) {
                client.player.sendChatMessage(messageToSend);
                this.lastTime = System.currentTimeMillis();
                return;
            }
        }
    }

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At("HEAD"))
    private void sendRespect(Text message, int messageId, int timestamp, boolean bl, CallbackInfo ci) {
        if (client.getCurrentServerEntry().address.contains("hypixel.net")) {
            if (AutoTechno.config.killMessages) {
                if (hypixelFinalStrings.size() == 0) populateHypixelFinalStrings();
                processChat(message, hypixelFinalStrings, "Blood for the Blood God");
            }
            if (AutoTechno.config.endMessages) {
                if (hypixelEndStrings.size() == 0) populateHypixelEndStrings();
                processChat(message, hypixelEndStrings, "Rest in Peace Technoblade");
            }
            if (AutoTechno.config.startMessages) {
                if (hypixelStartStrings.size() == 0) populateHypixelStartStrings();
                processChat(message, hypixelStartStrings, "Good luck, and may Techno's unmatched skill be in you");
            }
        } else if (client.getCurrentServerEntry().address.contains("bedwarspractice.club")) {
            if (AutoTechno.config.killMessages) {
                if (bedwarsPracticeFinalStrings.size() == 0) populateBedwarsPracticeFinalStrings();
                processChat(message, bedwarsPracticeFinalStrings, "Blood for the Blood God");
            }
            if (AutoTechno.config.endMessages) {
                if (bedwarsPracticeEndStrings.size() == 0) populateBedwarsPracticeEndStrings();
                processChat(message, bedwarsPracticeEndStrings, "Rest in Peace Technoblade");
            }
            if (AutoTechno.config.startMessages) {
                if (bedwarsPracticeStartStrings.size() == 0) populateBedwarsPracticeStartStrings();
                processChat(message, bedwarsPracticeStartStrings, "Good luck, and may Techno's unmatched skill be in you");
            }
        } else if (client.getCurrentServerEntry().address.contains("pvp.land")) {
            if (AutoTechno.config.killMessages) {
                if (pvpLandFinalStrings.size() == 0) populatePvpLandFinalStrings();
                processChat(message, pvpLandFinalStrings, "Blood for the Blood God");
            }
            if (AutoTechno.config.endMessages) {
                if (pvpLandEndStrings.size() == 0) populatePvpLandEndStrings();
                processChat(message, pvpLandEndStrings, "Rest in Peace Technoblade");
            }
            if (AutoTechno.config.startMessages) {
                if (pvpLandStartStrings.size() == 0) populatePvpLandStartStrings();
                processChat(message, pvpLandStartStrings, "Good luck, and may Techno's unmatched skill be in you");
            }
        } else if (client.getCurrentServerEntry().address.contains("minemen.club")) {
            if (AutoTechno.config.killMessages) {
                if (minemenFinalStrings.size() == 0) populateMinemenFinalStrings();
                processChat(message, minemenFinalStrings, "Blood for the Blood God");
            }
            if (AutoTechno.config.endMessages) {
                if (minemenEndStrings.size() == 0) populateMinemenEndStrings();
                processChat(message, minemenEndStrings, "Rest in Peace Technoblade");
            }
            if (AutoTechno.config.startMessages) {
                if (minemenStartStrings.size() == 0) populateMinemenStartStrings();
                processChat(message, minemenStartStrings, "Good luck, and may Techno's unmatched skill be in you");
            }
        }
    }
}
