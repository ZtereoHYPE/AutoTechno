package codes.ztereohype.autotechno.client;

public enum Server {
    HYPIXEL("hypixel.net"),
    BEDWARS_PRACTICE("bedwarspractice.club"),
    PVPLAND("pvp.land"),
    MINEMEN("minemen.club"),
    MINEPLEX("mineplex.com");

    private final String ip;

    Server(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }
}
