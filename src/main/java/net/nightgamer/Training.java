package net.nightgamer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.nightgamer.Commands.easyCommands;
import net.nightgamer.Commands.invCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public final class Training extends JavaPlugin implements Listener {

    public File spawn;
    public File startItem;

    public FileConfiguration startItemConfig;
    public FileConfiguration spawnConfig;




    @Override
    public void onEnable() {
        spawn = new File(getDataFolder(), "spawn.yml");
        startItem = new File(getDataFolder(), "startItem.yml");

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        if(!spawn.exists()) {
            try {
                spawn.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(!startItem.exists()) {
            try {
                startItem.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        spawnConfig = YamlConfiguration.loadConfiguration(spawn);
        startItemConfig = YamlConfiguration.loadConfiguration(startItem);


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
        getCommand("head").setExecutor(new easyCommands(this));
        getCommand("enderc").setExecutor(new easyCommands(this));
        getCommand("wb").setExecutor(new easyCommands(this));
        getCommand("explode").setExecutor(new easyCommands(this));
        getCommand("players").setExecutor(new easyCommands(this));

        getServer().getPluginManager().registerEvents(this, this);


        getCommand("lol").setExecutor(new invCommand());


    }

    @Override
    public void onDisable() {
        System.out.println("[NightGamer Training]: Plugin has been disabled!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)  {
        Player player = event.getPlayer();
        if(!startItemConfig.contains("UUID." + player.getUniqueId().toString())) {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            player.getInventory().addItem(head);
            startItemConfig.set("UUID." + player.getUniqueId().toString(), player.getUniqueId().toString());

            try {
               startItemConfig.save(startItem);
           } catch (IOException e) {
               e.printStackTrace();
           }


        }

        Bukkit.getServer().broadcast(Component.text("[NightGamer Training]: " + player.getName() + " joined the game!"));



        if(!spawnConfig.getBoolean("spawn") && player.isOp()) {
            player.sendMessage(Component.text("Spawn not set"));
        } else {
            double x = spawnConfig.getDouble("SpawnX");
            double y = spawnConfig.getDouble("SpawnY");
            double z = spawnConfig.getDouble("SpawnZ");
            String world = spawnConfig.getString("SpawnWorld");
            float yaw = (float) spawnConfig.getDouble("Spawnyaw");
            float pitch = (float) spawnConfig.getDouble("Spawnpitch");


            if (world != null && !player.isOp()) {
                Location loc  = new Location(Bukkit.getWorld(world), x,y,z,yaw,pitch);
                event.getPlayer().teleport(loc);
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


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.deathMessage(Component.text("[NightGamer Training]: " + e.deathMessage()));
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        if (p.hasPermission("nightgamer.admin")) {
            e.setMessage("&4&l[Admin] " + message);
        }

    }
    @EventHandler
    public void onPlayerclick(PlayerInteractEvent e){

            if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PLAYER_HEAD) {

                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

                    Snowball ball = e.getPlayer().launchProjectile(Snowball.class, e.getPlayer().getLocation().getDirection());
                    ball.setGlowing(true);
                    ball.setCustomNameVisible(true);
                    ball.setGravity(false);
                    ball.customName(Component.text("Snowball"));

                }

            }

    }

}
