package net.nightgamer.Commands;

import net.kyori.adventure.text.Component;
import net.nightgamer.Training;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.logging.Logger;

public class serverCommands implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

      if (!(sender instanceof Player)){

          StringBuilder s = new StringBuilder();

          if (args.length >= 1){

              for (int i=1; i<args.length; i++){

                  s.append(args[i]).append(" ");
              }

              Bukkit.getServer().broadcast(Component.text(s.toString()));
          } else  {
              Bukkit.getLogger().info("usage: /say <text>");
          }





      } else if (sender instanceof Player){
          Player p = (Player) sender;
          p.sendMessage(Component.text("Only consol is allowed to use this command!"));

      }





        return false;
    }
}
