package com.xosurvival.xodupes.Commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import com.xosurvival.xodupes.Main;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
        String undupeable_message = main.getConfig().getString("undupeable-message");
        String full_inventory_message = main.getConfig().getString("full-inventory-message");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Material type = player.getInventory().getItemInMainHand().getType();
            if (sender.hasPermission("xo.dupe")) {
                ItemStack item = player.getInventory().getItemInMainHand();
                if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(prefix_message + ' ' + blank_hand_message));
                } else if (Tag.SHULKER_BOXES.isTagged(item.getType())) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(prefix_message + ' ' + undupeable_message));
                } else if (type == Material.SHULKER_SHELL || type == Material.BARRIER || type == Material.BEDROCK || type == Material.DRAGON_EGG || type == Material.SPAWNER) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(prefix_message + ' ' + undupeable_message));
                } else {
                    PlayerInventory inventory = player.getInventory();
                    if (inventory.firstEmpty() == -1) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize(prefix_message + ' ' + full_inventory_message));
                    } else {
                        item.clone();
                        ItemStack dupeditem = player.getInventory().getItemInMainHand().clone();
                        player.getInventory().addItem(dupeditem);
                    }
                }
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

