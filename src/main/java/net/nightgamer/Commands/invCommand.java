package net.nightgamer.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.nightgamer.superClasses.PlayerClass;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class invCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();


        Inventory inv = Bukkit.createInventory(null, 18, Component.text("[NightGamer Training]").color(NamedTextColor.GOLD));

        if (args.length == 0 && p.hasPermission("nightgamer.inv")) {

            PlayerClass pC = new PlayerClass(
                    p.getUniqueId(),
                    p.getGameMode()
            );

            if (pC.getGamemode().equals(GameMode.CREATIVE) && p.isOp()) {
                p.openInventory(inv);
            }



        }





        return false;
    }
}
