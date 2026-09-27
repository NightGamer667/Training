package net.nightgamer.superClasses;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerClass {


    private UUID uuid;
    private GameMode gamemode;

    public PlayerClass(){

    }

    public PlayerClass(UUID uuid){

        this.uuid = uuid;
    }

    public PlayerClass(UUID uuid, GameMode gamemode){

        this.uuid = uuid;
        this.gamemode = gamemode;
    }




    public UUID getUuid() {
        return uuid;
    }

    public GameMode getGamemode() {
        return gamemode;
    }
}
