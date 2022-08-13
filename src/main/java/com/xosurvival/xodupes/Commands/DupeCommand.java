package com.xosurvival.xodupes.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import com.xosurvival.xodupes.Main;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DupeCommand implements CommandExecutor {
    private Main main;

    public DupeCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix_message = main.getConfig().getString("prefix-message");
        String no_permission_message = main.getConfig().getString("no-permission-message");
        String blank_hand_message = main.getConfig().getString("blank-hand-message");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("xo.dupe")) {
                ItemStack item = player.getInventory().getItemInMainHand();
                // if check to figure out how to check if it's an item
                sender.sendMessage(MiniMessage.miniMessage().deserialize(String.valueOf(item)));
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(prefix_message + ' ' + no_permission_message, Placeholder.component("permission", Component.text("xo.dupe"))));
            }
        } else {
            String non_player_message = main.getConfig().getString("non-player-message");
            sender.sendMessage(MiniMessage.miniMessage().deserialize(non_player_message));
        }
        return true;
    }
}

