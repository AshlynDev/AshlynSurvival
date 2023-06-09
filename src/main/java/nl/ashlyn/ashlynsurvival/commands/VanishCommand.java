package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class VanishCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Player p = (Player)sender;
            if (!p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 99999));
                p.sendMessage("§cNow you're not longer Visible");
            }
            else {
                p.removePotionEffect(PotionEffectType.INVISIBILITY);
                p.sendMessage("§aNow you're Visible!");
            }
        return true;
    }
}

