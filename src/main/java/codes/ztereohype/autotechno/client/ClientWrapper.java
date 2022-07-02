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

        for (Server server : Server.values()) {
            if (address.contains(server.getIp())) return server;
        }
        return null;
    }

    public void sendMessage(String messageToSend) {
        client.player.sendChatMessage(messageToSend);
    }
}
