package net.nightgamer.Commands;

import net.kyori.adventure.text.Component;
import net.nightgamer.Training;
import net.nightgamer.superClasses.PlayerClass;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import net.nightgamer.enums.gm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class easyCommands implements CommandExecutor  {

    Training main;
    gm gam;
    PlayerClass pC;


    public easyCommands(PlayerClass pC){this.pC = pC;}
    public easyCommands(Training main) {
        this.main = main;
    }


    List<UUID> playerList = new ArrayList<>();

    float speed;





    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {



        if ((sender instanceof Player)) {
            Player p = (Player) sender;


            switch(command.getName().toLowerCase()) {

                //region heal
                case "heal" :
                    if(p.hasPermission("nightgamer.heal")) {
                        p.setHealth(20);
                        p.setFoodLevel(20);
                        p.setSaturation(20);
                    }
                break;
                //endregion

                //region spawn
                case "spawn":
                    if(p.hasPermission("nightgamer.spawn") && main.getConfig().getBoolean("spawn")) {



                        double x = main.spawnConfig.getDouble("SpawnX");
                        double y = main.spawnConfig.getDouble("SpawnY");
                        double z = main.spawnConfig.getDouble("SpawnZ");
                        String world = main.spawnConfig.getString("SpawnWorld");
                        Float yaw = (Float) main.spawnConfig.get("Spawnyaw");
                        Float pitch = (Float) main.spawnConfig.get("Spawnpitch");


                        Location loc = new Location(Bukkit.getWorld(world), x, y,z, yaw, pitch);
                        p.teleport(loc);
                    } else {
                        p.sendMessage(Component.text("no spawn Set"));
                    }
                break;
                //endregion

                //region setspawm
                case "setspawn":
                    if(p.hasPermission("nightgamer.setspawn") && !main.spawnConfig.getBoolean("spawn")) {

                        main.spawnConfig.set("SpawnX", p.getLocation().getX());
                        main.spawnConfig.set("SpawnY", p.getLocation().getY());
                        main.spawnConfig.set("SpawnZ", p.getLocation().getZ());
                        main.spawnConfig.set("SpawnWorld", p.getLocation().getWorld().getName());
                        main.spawnConfig.set("Spawnyaw", p.getLocation().getYaw());
                        main.spawnConfig.set("Spawnpitch", p.getLocation().getPitch());
                        main.spawnConfig.set("spawn", true);
                        try {
                            main.spawnConfig.save(main.spawn);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                break;
                //endregion

                //region hunger
                case "hunger":
                    if(p.hasPermission("nightgamer.hunger")) {
                        p.setFoodLevel(20);
                    }
                break;
                //endregion

                //region kill
                case "kill":
                    if(p.hasPermission("nightgamer.kill")) {
                        if(args.length == 1) {

                            Player target = Bukkit.getPlayer(args[0]);
                            if(target != null) {
                                target.setHealth(0);
                            }
                        }

                    } else if (!(sender instanceof Player)) {
                        Bukkit.getServer().sendMessage(Component.text("you need to be a Player to use this command!"));
                    }
                break;
                //endregion

                //region fly
                case "fly":


                    if(p.hasPermission("nightgamer.fly") && !playerList.contains(p.getUniqueId())) {

                        p.setFlying(true);
                        p.setAllowFlight(true);
                        playerList.add(p.getUniqueId());

                    } else if(p.hasPermission("nightgamer.fly") ) {
                        p.setFlying(false);
                        p.setAllowFlight(false);
                        playerList.remove(p.getUniqueId());

                    }
                break;
                //endregion

                //region day

                case "day":
                    if(p.hasPermission("nightgamer.day")) {
                        p.sendMessage(Component.text("it is Day now"));
                        p.getWorld().setTime(6000);
                    }
                break;

                //endregion

                //region night

                case "night":
                    if(p.hasPermission("nightgamer.night")) {
                        p.sendMessage(Component.text("it is Night now"));
                        p.getWorld().setTime(12000);
                    }
                break;

                //endregion

                //region gm
                case "gm":
                    if(p.hasPermission("nightgamer.gm")) {
                        if(args.length == 2) {
                            switch(args[1].toLowerCase()) {
                                case "creative":
                                    p.setGameMode(gm.Creative.getGameMode());
                                    break;
                                case "spectator":
                                    p.setGameMode(gm.Spectator.getGameMode());
                                break;
                                case "adventure":
                                    p.setGameMode(gm.Adventure.getGameMode());
                                break;

                                case "survival":
                                    p.setGameMode(gm.Survival.getGameMode());
                                break;


                            }
                        }
                    }
                break;
                //endregion

                //region flyspeed

                case "flyspeed":

                    if(p.hasPermission("nightgamer.flyspeed") && args.length == 2) {
                        if(args[1].matches("[0-9]+")) {
                            p.setFlySpeed(Float.parseFloat(args[1]));
                        }
                    }

                break;
                //endregion

                //region walkspeed

                case "walkspeed":

                    if(p.hasPermission("nightgamer.walkspeed") && args.length == 2) {
                        if(args[1].matches("[0-9]+")) {
                            p.setWalkSpeed(Float.parseFloat(args[1]));
                        }
                    }

                    break;
                //endregion

                //region clearinv
                case "clearinv":
                    if(p.hasPermission("nightgamer.clearinv") && args.length == 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null) {
                            target.getInventory().clear();

                        }
                    }
                break;

                //endregion

                //region sun
                case "sun":
                    if(p.hasPermission("nightgamer.sun")) {
                        p.getWorld().setStorm(false);
                    }
                break;



                //endregion

                //region rain

                case "rain":
                    if(p.hasPermission("nightgamer.rain")) {
                        p.getWorld().setStorm(true);
                    }
                break;


                //endregion

                //region storm
                case "storm":
                    if(p.hasPermission("nightgamer.storm")) {
                        p.getWorld().setStorm(true);
                        p.getWorld().setThundering(true);
                    }
                break;


                //endregion

                //region regionblanko



                //endregion


            }

        } else {
            System.out.println("only a Player can execute this command");
        }




        return true;
    }
}
