package net.nightgamer.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.nightgamer.Training;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class message implements CommandExecutor {

    private Training main;
    public message(Training main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length >= 2) {
                Player target = Bukkit.getPlayerExact(args[0]);
                StringBuilder message = new StringBuilder();


                if(target != null) {


                    for(int i = 1; i < args.length; i++) {
                        message.append(args[i]).append(" ");
                    }

                    p.sendMessage("You -> " + target.getName() + " " + Component.text(message.toString()).color(NamedTextColor.BLUE));

                    target.sendMessage("From -> " + p.getName() + " " + Component.text(message.toString()).color(NamedTextColor.GREEN));

                    main.getLastmessage().put(p.getUniqueId(), target.getUniqueId());
                } else {
                    p.sendMessage(Component.text("That player is not Online").color(NamedTextColor.RED));
                }

            } else {
                p.sendMessage(Component.text("Usage: /msg <Player> <message>"));
            }


        }



        return false;
    }
}
