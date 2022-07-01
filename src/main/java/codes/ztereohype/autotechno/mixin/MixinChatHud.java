package codes.ztereohype.autotechno.mixin;

import java.util.ArrayList;
import java.util.List;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.chat.EventDetector;
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
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At("HEAD"))
    private void sendRespect(Text message, int messageId, int timestamp, boolean bl, CallbackInfo ci) {
       AutoTechno.messageRandomiser.getRandomMessage(AutoTechno.detector.scanForEvent(message.getString()));
    }
}
