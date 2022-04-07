package uk.debb.autogg.mixin;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import uk.debb.autogg.AutoGG;

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

    @Unique private List<String> hypixelGGStrings = new ArrayList<String>();
    @Unique private List<String> bedwarsPracticeGGStrings = new ArrayList<String>();
    @Unique private List<String> pvpLandGGStrings = new ArrayList<String>();

    @Unique private void populateHypixelStrings() {
        hypixelGGStrings.add("1st Killer -");
        hypixelGGStrings.add("1st Place -");
        hypixelGGStrings.add("Winner:");
        hypixelGGStrings.add(" - Damage Dealt -");
        hypixelGGStrings.add("Winning Team -");
        hypixelGGStrings.add("1st -");
        hypixelGGStrings.add("Winners:");
        hypixelGGStrings.add("Winner:");
        hypixelGGStrings.add("Winning Team:");
        hypixelGGStrings.add(" won the game!");
        hypixelGGStrings.add("Top Seeker:");
        hypixelGGStrings.add("1st Place:");
        hypixelGGStrings.add("Last team standing!");
        hypixelGGStrings.add("Winner #1 (");
        hypixelGGStrings.add("Top Survivors");
        hypixelGGStrings.add("Winners -");
        hypixelGGStrings.add("Sumo Duel -");
    }

    @Unique private void populateBedwarsPracticeStrings() {
        bedwarsPracticeGGStrings.add("Winners -");
        bedwarsPracticeGGStrings.add("Game Won!");
        bedwarsPracticeGGStrings.add("Game Lost!");
        bedwarsPracticeGGStrings.add("The winning team is");
    }

    @Unique private void populatePvpLandStrings() {
        pvpLandGGStrings.add("The match has ended!");
        pvpLandGGStrings.add("Match Results");
        pvpLandGGStrings.add("Winner:");
        pvpLandGGStrings.add("Loser:");
    }

    @Unique private void processChat(Text messageRecieved, List<String> options, String messageToSend) {
        for (String s : options) {
            if (messageRecieved.getString().contains(s)) {
                client.player.sendChatMessage(messageToSend);
                this.lastTime = System.currentTimeMillis();
                return;
            }
        }
    }

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At("HEAD"), cancellable = true)
    private void typeGG(Text message, int messageId, int timestamp, boolean bl, CallbackInfo ci) {
        if (System.currentTimeMillis() - this.lastTime <= 3000) return;
        if (client.getCurrentServerEntry().address.contains("hypixel.net")) {
            if (hypixelGGStrings.size() == 0) populateHypixelStrings();
            if (AutoGG.config.ggMessages) {
                processChat(message, hypixelGGStrings, "gg");
            }
        } else if (client.getCurrentServerEntry().address.contains("bedwarspractice.club")) {
            if (bedwarsPracticeGGStrings.size() == 0) populateBedwarsPracticeStrings();
            if (AutoGG.config.ggMessages) {
                processChat(message, bedwarsPracticeGGStrings, "gg");
            }
        } else if (client.getCurrentServerEntry().address.contains("pvp.land")) {
            if (pvpLandGGStrings.size() == 0) populatePvpLandStrings();
            if (AutoGG.config.ggMessages) {
                processChat(message, pvpLandGGStrings, "gg");
            }
        }
    }
}
