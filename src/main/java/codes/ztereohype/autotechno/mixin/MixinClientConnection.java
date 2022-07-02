package codes.ztereohype.autotechno.mixin;

import codes.ztereohype.autotechno.AutoTechno;
import codes.ztereohype.autotechno.chat.EventDetector;
import codes.ztereohype.autotechno.client.Server;
import net.minecraft.network.ClientConnection;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    @Inject(method = "disconnect", at = @At("HEAD"))
    private void onDisconnect(Text disconnectReason, CallbackInfo ci) {
        if (AutoTechno.client.getCurrentServer() == Server.MINEPLEX) {
            EventDetector.mineplexStart = false;
        }
    }
}