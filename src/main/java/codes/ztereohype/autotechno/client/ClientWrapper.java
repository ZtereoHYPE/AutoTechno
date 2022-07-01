package codes.ztereohype.autotechno.client;

import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Nullable;

public class ClientWrapper {
    private final MinecraftClient client;

    public ClientWrapper() {
        this.client = MinecraftClient.getInstance();
    }

    public MinecraftClient getClient() {
        return this.client;
    }

    public @Nullable KnownServer getCurrentServer() {
        String address = client.getCurrentServerEntry().address;

        //todo: change to a matcher
        if (address.contains("hypixel.net")) return KnownServer.HYPIXEL;
        else if (address.contains("bedwarspractice.club")) return KnownServer.BEDWARS_PRACTICE;
        else if (address.contains("pvp.land")) return KnownServer.PVPLAND;
        else if (address.contains("minemen.club")) return KnownServer.MINEMEN;
        else return null;
    }

    public void sendMessage(String messageToSend) {
        client.player.sendChatMessage(messageToSend);
    }
}
