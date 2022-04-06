package uk.debb.autogg.mixin;

import java.util.ArrayList;
import java.util.List;
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
    @Shadow @Final private MinecraftClient client;

    @Unique private List<String> hypixelStrings = new ArrayList<String>();
    @Unique private List<String> bedwarsPracticeStrings = new ArrayList<String>();
    @Unique private List<String> pvpLandStrings = new ArrayList<String>();

    @Unique private void populateHypixelStrings() {
        hypixelStrings.add("1st Killer -");
        hypixelStrings.add("1st Place -");
        hypixelStrings.add("Winner:");
        hypixelStrings.add(" - Damage Dealt -");
        hypixelStrings.add("Winning Team -");
        hypixelStrings.add("1st -");
        hypixelStrings.add("Winners:");
        hypixelStrings.add("Winner:");
        hypixelStrings.add("Winning Team:");
        hypixelStrings.add(" won the game!");
        hypixelStrings.add("Top Seeker:");
        hypixelStrings.add("1st Place:");
        hypixelStrings.add("Last team standing!");
        hypixelStrings.add("Winner #1 (");
        hypixelStrings.add("Top Survivors");
        hypixelStrings.add("Winners -");
        hypixelStrings.add("Sumo Duel -");
    }

    @Unique private void populateBedwarsPracticeStrings() {
        bedwarsPracticeStrings.add("Winners -");
        bedwarsPracticeStrings.add("Game Won!");
        bedwarsPracticeStrings.add("Game Lost!");
        bedwarsPracticeStrings.add("The winning team is");
    }

    @Unique private void populatePvpLandStrings() {
        pvpLandStrings.add("The match has ended!");
        pvpLandStrings.add("Match Results");
        pvpLandStrings.add("Winner:");
        pvpLandStrings.add("Loser:");
    }

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At("HEAD"), cancellable = true)
    private void typeGG(Text message, int messageId, int timestamp, boolean bl, CallbackInfo ci) {
        if (client.getCurrentServerEntry().address.contains("hypixel.net")) {
            if (hypixelStrings.size() == 0) {
                populateHypixelStrings();
            }
            for (String s : hypixelStrings) {
                if (message.getString().contains(s)) {
                    client.player.sendChatMessage("gg");
                    return;
                }
            }
        } else if (client.getCurrentServerEntry().address.contains("bedwarspractice.club")) {
            if (bedwarsPracticeStrings.size() == 0) {
                populateBedwarsPracticeStrings();
            }
            for (String s : bedwarsPracticeStrings) {
                if (message.getString().contains(s)) {
                    client.player.sendChatMessage("gg");
                    return;
                }
            }
        } else if (client.getCurrentServerEntry().address.contains("pvp.land")) {
            if (pvpLandStrings.size() == 0) {
                populatePvpLandStrings();
            }
            for (String s : pvpLandStrings) {
                if (message.getString().contains(s)) {
                    client.player.sendChatMessage("gg");
                    return;
                }
            }
        }
    }
}
