package net.nightgamer;

import net.kyori.adventure.text.Component;
import net.nightgamer.Commands.invCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public final class Training extends JavaPlugin implements Listener {

    public File spawn;
    public FileConfiguration spawnConfig;




    @Override
    public void onEnable() {
        spawn = new File(getDataFolder(), "spawn");

        if(!spawn.exists()) {
            try {
                spawn.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawn);


        System.out.println("[NightGamer Training]: Plugin has been enabled!");

        getCommand("spawn").setExecutor(new invCommand());
        getCommand("setspawn").setExecutor(new invCommand());
        getCommand("heal").setExecutor(new invCommand());
        getCommand("hunger").setExecutor(new invCommand());
        getCommand("kill").setExecutor(new invCommand());
        getCommand("fly").setExecutor(new invCommand());
        getCommand("day").setExecutor(new invCommand());
        getCommand("night").setExecutor(new invCommand());



    }

    @Override
    public void onDisable() {
        System.out.println("[NightGamer Training]: Plugin has been disabled!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();


        if(!spawnConfig.getBoolean("spawn") && player.isOp()) {
            player.sendMessage(Component.text("Spawn not set"));
        } else{

            double x = spawnConfig.getDouble("SpawnX");
            double y = spawnConfig.getDouble("SpawnY");
            double z = spawnConfig.getDouble("SpawnZ");
            String world = spawnConfig.getString("SpawnWorld");
            float yaw = (float) spawnConfig.getDouble("Spawnyaw");
            float pitch = (float) spawnConfig.getDouble("Spawnpitch");

            Location loc  = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
            if (!world.isBlank()) {
                player.teleport(loc);
            } else{
                player.sendMessage(Component.text("Spawn location is null"));
            }

        }



    }
}
