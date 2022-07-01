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

    public @Nullable Server getCurrentServer() {
        if (client.getCurrentServerEntry() == null) return null;
        String address = client.getCurrentServerEntry().address;

        //todo: change to a matcher
        if (address.contains("hypixel.net")) return Server.HYPIXEL;
        else if (address.contains("bedwarspractice.club")) return Server.BEDWARS_PRACTICE;
        else if (address.contains("pvp.land")) return Server.PVPLAND;
        else if (address.contains("minemen.club")) return Server.MINEMEN;
        else return null;
    }

    public void sendMessage(String messageToSend) {
        client.player.sendChatMessage(messageToSend);
    }
}
