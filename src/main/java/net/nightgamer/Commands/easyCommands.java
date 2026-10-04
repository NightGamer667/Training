package net.nightgamer.Commands;

import io.papermc.paper.ban.BanListType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.nightgamer.Training;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import net.nightgamer.enums.gm;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class easyCommands implements CommandExecutor  {

    Training main;





    public easyCommands(Training main) {
        this.main = main;
    }



    List<UUID> playerList = new ArrayList<>();







    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        boolean opSpawn = main.spawnConfig.getBoolean("opspawn");

        if ((sender instanceof Player p)) {
            double doubleSpeed;

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
                    if(p.hasPermission("nightgamer.spawn") && main.spawnConfig.getBoolean("spawn")) {



                        double x = main.spawnConfig.getDouble("SpawnX");
                        double y = main.spawnConfig.getDouble("SpawnY");
                        double z = main.spawnConfig.getDouble("SpawnZ");
                        String world = main.spawnConfig.getString("SpawnWorld");
                        float yaw = (float) main.spawnConfig.getDouble("Spawnyaw");
                        float pitch = (float) main.spawnConfig.getDouble("Spawnpitch");



                        Location loc = new Location(Bukkit.getWorld(Objects.requireNonNull(world)), x, y,z, yaw, pitch);
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
                        p.getWorld().setTime(18000);
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


                    if(p.hasPermission("nightgamer.flyspeed") && args.length == 1) {

                        try {
                            doubleSpeed = Double.parseDouble(args[0]);
                            if(doubleSpeed < -1.0 || doubleSpeed > 1.0) {
                                p.sendMessage(Component.text("Please enter a valid number between -1.0 and 1.0"));
                                return true;
                            }

                            p.setFlySpeed((float) doubleSpeed);
                            p.sendMessage(Component.text("Speed: " + p.getFlySpeed()));

                        } catch (NumberFormatException e) {
                            p.sendMessage(Component.text("Please enter a valid number between -1.0 and 1.0"));
                        }
                    }

                break;
                //endregion

                //region walkspeed

                case "walkspeed":


                    if(p.hasPermission("nightgamer.walkspeed") && args.length == 1) {

                        try {
                            doubleSpeed = Double.parseDouble(args[0]);
                            if(doubleSpeed < -1.0 || doubleSpeed > 1.0) {
                                p.sendMessage(Component.text("Please enter a valid number between -1.0 and 1.0"));
                                p.sendMessage(Component.text("Speed: " + p.getWalkSpeed()));
                                return true;
                            }

                            p.setWalkSpeed((float) doubleSpeed);

                        } catch (NumberFormatException e) {
                            p.sendMessage(Component.text("Please enter a valid number between -1.0 and 1.0"));
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

                //region wb
                case "wb":
                    if(p.hasPermission("nightgamer.wb")) {
                        p.openWorkbench(null, true);
                    }
                break;

                //endregion

                //region enderc
                case "enderc":
                    if(p.hasPermission("nightgamer.enderc")) {
                        Inventory in = p.getEnderChest();
                        p.openInventory(in);
                    }

                break;
                //endregion

                //region head
                case "head":
                    if(p.hasPermission("nightgamer.head")) {


                        if(p.getEquipment().getItemInMainHand().getType() != Material.AIR) {

                            p.getInventory().setHelmet(p.getEquipment().getItemInMainHand());

                        }
                    }



                //endregion

                //region explode
                case "explode":
                    if(p.hasPermission("nightgamer.explode")) {
                        p.getWorld().createExplosion(p.getLocation(), 100, false, false);
                    }
                break;

                //endregion

                //region who
                case "who":

                    if(p.hasPermission("nightgamer.who")) {
                        StringBuilder players = new StringBuilder();
                        for (Player player : Bukkit.getOnlinePlayers()) {

                            players.append(player.getName()).append(", ");

                        }
                        p.sendMessage(Component.text(players.toString()));
                    }


                break;
                //endregion

                //region location
                case "location":

                    if(p.hasPermission("nightgamer.location")) {
                        p.sendMessage(Component.text("You are here\nX "
                                + p.getLocation().getX() + "\nY "
                                +  p.getLocation().getY() + "\nZ "
                                + p.getLocation().getZ()));
                    }


                break;
                //endregion

                //region world
                case "world":

                    if(p.hasPermission("nightgamer.world")) {
                        p.sendMessage(Component.text(p.getWorld().getName()));
                    }

                break;
                //endregion

                //region players
                case "players":
                    if(p.hasPermission("nightgamer.players")) {
                        p.sendMessage(Component.text(Bukkit.getOnlinePlayers().size() + " players online"));
                    }

                break;
                //endregion

                //region opspawn
                case "opspawn":
                    if(p.hasPermission("nightgamer.opspawn")) {
                        opSpawn = !opSpawn;
                    }
                break;

                //endregion

                //region punish

                case "punish":
                    if(p.hasPermission("nightgamer.punish")) {
                        if (args.length == 4) {
                            if (Bukkit.getPlayer(args[0]) != null) {
                                Player target = Bukkit.getPlayer(args[0]);
                                Component source = Component.text("NightGames").color(NamedTextColor.YELLOW);


                                switch (args[1]) {

                                    case "ban":

                                        if (args.length > 3) {
                                            p.sendMessage(Component.text("you dont need to set the time in ban"));
                                        }

                                        try {
                                            target.ban(args[2],
                                                    (Date) null,
                                                    String.valueOf(source),
                                                    true);

                                        } catch (NullPointerException e){
                                            e.fillInStackTrace();
                                            p.sendMessage(Component.text("Player not found"));
                                        }



                                    break;

                                    case "kick":

                                        if (args.length > 3) {
                                            p.sendMessage(Component.text("you dont need to set the time in kick"));
                                        }
                                        try {
                                            target.kick(Component.text(
                                                    args[2] +
                                                            p.getName()).color(NamedTextColor.DARK_RED));

                                        } catch (NullPointerException e){
                                            e.printStackTrace();
                                            p.sendMessage(Component.text("Player not found"));
                                        }

                                    break;

                                    case "banh":

                                        if (args.length < 4) {
                                            p.sendMessage(Component.text("Usage: /punish <player> banh <reason> <hours>"));
                                            break;
                                        }

                                        try {

                                            Calendar cal = Calendar.getInstance();
                                            int hour = Integer.parseInt(args[3]);
                                            cal.add(Calendar.HOUR, hour);

                                            target.ban(args[2],
                                                    cal.getTime(),
                                                    String.valueOf(source),
                                                    true);

                                        } catch (NumberFormatException e) {

                                            e.printStackTrace();
                                            p.sendMessage(Component.text("Please enter a valid number"));

                                        }

                                    break;

                                    default:

                                        p.sendMessage(Component.text("usage /punish, target, ban/kick/banh, message, time with banh"));

                                    break;


                                }


                            }

                        }
                    }



                //endregion

                //region blanko

                //endregion


            }

        } else {
            System.out.println("only a Player can execute this command");
        }




        return true;
    }
}
