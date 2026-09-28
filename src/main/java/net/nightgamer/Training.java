package net.nightgamer;

import net.kyori.adventure.text.Component;
import net.nightgamer.Commands.easyCommands;
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
import org.bukkit.event.player.PlayerQuitEvent;
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

        getCommand("spawn").setExecutor(new easyCommands(this));
        getCommand("setspawn").setExecutor(new easyCommands(this));
        getCommand("heal").setExecutor(new easyCommands(this));
        getCommand("hunger").setExecutor(new easyCommands(this));
        getCommand("kill").setExecutor(new easyCommands(this));
        getCommand("fly").setExecutor(new easyCommands(this));
        getCommand("day").setExecutor(new easyCommands(this));
        getCommand("night").setExecutor(new easyCommands(this));
        getCommand("flyspeed").setExecutor(new easyCommands(this));
        getCommand("walkspeed").setExecutor(new easyCommands(this));
        getCommand("clearinv").setExecutor(new easyCommands(this));
        getCommand("sun").setExecutor(new easyCommands(this));
        getCommand("rain").setExecutor(new easyCommands(this));
        getCommand("storm").setExecutor(new easyCommands(this));

        getCommand("lol").setExecutor(new invCommand());


    }

    @Override
    public void onDisable() {
        System.out.println("[NightGamer Training]: Plugin has been disabled!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Bukkit.getServer().sendMessage(Component.text("[NightGamer Training]: " + player.getName() + " joined the game!"));

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
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        Bukkit.getServer().sendMessage(Component.text("[NightGamer Training]: " + p.getName() + " left the game!"));

    }

}
