package codes.ztereohype.autotechno.mixin;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.chat.Event;
import codes.ztereohype.autotechno.config.AutoTechnoConfig;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class MixinChatHud {
    @Unique
    private static long lastTime;

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;ILnet/minecraft/client/gui/hud/MessageIndicator;Z)V", at = @At("HEAD"))
    private void sendRespect(Text message, MessageSignatureData signature, int ticks, MessageIndicator indicator, boolean refresh, CallbackInfo ci) {
        Event event = AutoTechno.detector.scanForEvent(message.getString());

        if (event != null) {
            if (System.currentTimeMillis() - lastTime <= Integer.parseInt((String) AutoTechnoConfig.getProperty("MessageWaitTime"))) return;
            lastTime = System.currentTimeMillis();

            String technoMessage = AutoTechno.messageRandomiser.getRandomMessage(event);
            AutoTechno.client.sendMessage(technoMessage);
        }
    }
}