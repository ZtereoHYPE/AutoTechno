package codes.ztereohype.autotechno.mixin;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.chat.Event;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class MixinChatHud {
    @Unique
    private final static long WAIT_TIME = 3000L;
    @Unique
    private static long lastTime;

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At("HEAD"))
    private void sendRespect(Text message, int messageId, int timestamp, boolean bl, CallbackInfo ci) {
        Event event = AutoTechno.detector.scanForEvent(message.getString());

        if (event != null) {
            if (System.currentTimeMillis() - lastTime <= WAIT_TIME) return;
            lastTime = System.currentTimeMillis();

            String technoMessage = AutoTechno.messageRandomiser.getRandomMessage(event);
            AutoTechno.client.sendMessage(technoMessage);
        }
    }
}