package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info("Je moet een speler zijn om dit command uit te voeren!");
            return true;
        }

        Player player = (Player)sender;
        switch (command.getName().toLowerCase()) {
            case "tpa":
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Je moet een speler noemen!");
                    return true;
                }

                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Ik kan speler \"" + args[0] + "\" niet vinden!");
                    return true;
                }

                if (target == player) {
                    player.sendMessage(ChatColor.RED + "Je kan niet naar jezelf Teleporteren!");
                    return true;
                }

                target.sendMessage(ChatColor.GOLD + args[0] + (ChatColor.GREEN + " wil naar je teleporteren. Type " + (ChatColor.GRAY + "/tpaccept") + (ChatColor.GREEN + " om de request te accepteren!")));
                player.sendMessage(ChatColor.GREEN + "Sent a teleport request to " + (ChatColor.GOLD + args[0] + (ChatColor.GREEN + "!")));
                break;

            case "tpaccept":
                break;

            case "tpahere":
                break;
        }

        return true;
    }
}
