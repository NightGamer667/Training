package net.nightgamer.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.nightgamer.Training;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class replymessage implements CommandExecutor {

    private Training main;

    public replymessage(Training main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length >= 1) {
                if (main.getLastmessage().containsKey(p.getUniqueId())) {

                    UUID uuid = main.getLastmessage().get(p.getUniqueId());

                    if (Bukkit.getPlayer(uuid) != null) {

                        Player target = Bukkit.getPlayer(uuid);
                        StringBuilder message = new StringBuilder();


                        if (target != null) {

                            for (int i = 0; i < args.length; i++) {
                                message.append(args[i]).append(" ");
                            }

                            p.sendMessage(Component.text("You -> " + target.getName()).append(Component.text(message.toString()).color(NamedTextColor.BLUE)));

                            target.sendMessage(Component.text("From -> " + p.getName()).append(Component.text(message.toString()).color(NamedTextColor.GREEN)));

                        } else {
                            p.sendMessage(Component.text("Player is not online!").color(NamedTextColor.RED));
                        }

                    } else {
                        p.sendMessage(Component.text("There is no last message for this command!").color(NamedTextColor.GOLD));
                    }

                }

            } else {
                p.sendMessage(Component.text("Wrong: usage is /reply <message> ").color(NamedTextColor.GOLD));
            }


        }
        return true;
    }
}