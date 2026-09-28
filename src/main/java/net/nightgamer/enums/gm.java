package net.nightgamer.enums;

import org.bukkit.GameMode;

public enum gm {
    Creative(GameMode.CREATIVE, "Kreative"),
    Survival(GameMode.SURVIVAL,  "Survival"),
    Adventure(GameMode.ADVENTURE,  "Adventure"),
    Spectator(GameMode.SPECTATOR,   "Spectator");

    private final GameMode gm;
    private final String name;

    gm(GameMode gm, String s) {
        this.gm = gm;
        this.name = s;
    }

    public GameMode getGameMode() {
        return gm;
    }

    public String getGameModeName() {
        return name;
    }

}

