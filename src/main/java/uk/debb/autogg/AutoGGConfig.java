package uk.debb.autogg;

public class AutoGGConfig {
    public boolean ggMessages;
    public boolean glhfMessages;
    public boolean gfMessages;
    public boolean lMessages;

    public AutoGGConfig(boolean ggMessages, boolean glhfMessages, boolean gfMessages, boolean lMessages) {
        this.ggMessages = ggMessages;
        this.glhfMessages = glhfMessages;
        this.gfMessages = gfMessages;
        this.lMessages = lMessages;
    }
}
