package nl.ashlyn.ashlynsurvival;

import nl.ashlyn.ashlynsurvival.commands.*;
import nl.ashlyn.ashlynsurvival.listener.SetHomeEvent;
import nl.ashlyn.ashlynsurvival.listener.onPlayerJoinLeaveEvent;
import nl.ashlyn.ashlynsurvival.listener.onPlayerSleepEvent;
import nl.ashlyn.ashlynsurvival.listener.onPlayerDeathEvent;
import nl.ashlyn.ashlynsurvival.util.SetHomeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;

public final class AshlynSurvival extends JavaPlugin {

    private File file = new File(getDataFolder(), "Homes.yml");
    public YamlConfiguration homes = YamlConfiguration.loadConfiguration(file);

    private FileConfiguration config = getConfig();

    private HashMap<Player, Integer> cooldownTimeHome;
    private HashMap<Player, BukkitRunnable> cooldownTaskHome;

    private HashMap<Player, Integer> cooldownTimeSetHome;
    private HashMap<Player, BukkitRunnable> cooldownTaskSetHome;

    private static final String prefixError = ChatColor.DARK_RED + "[" + ChatColor.RED + "*" + ChatColor.DARK_RED + "] " + ChatColor.GRAY;

    private SetHomeUtils utils = new SetHomeUtils(this);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equals("sethome")) {
            if (sender instanceof ConsoleCommandSender) {
                getLogger().log(Level.WARNING, "Only players can use this command.");

            } else if (sender instanceof Player) {
                Player player = (Player) sender;

                if (config.getBoolean("sethome-command-delay")) {
                    int coolDown = config.getInt("sethome-time-delay");
                    if (cooldownTimeSetHome.containsKey(player)) {
                        player.sendMessage(prefixError + "You must wait for " + ChatColor.RED + cooldownTimeSetHome.get(player) + ChatColor.GRAY + " seconds.");
                    } else {
                        setPlayerHome(player);
                        setCoolDownTimeSetHome(player, coolDown);
                    }
                } else {
                    setPlayerHome(player);
                }


            } else {
                sender.sendMessage(prefixError + "There was an error performing this command.");
            }

        } else if (command.getName().equals("home")) {

            if (sender instanceof ConsoleCommandSender) {
                getLogger().log(Level.WARNING, "Only players can use this command.");
            } else if (sender instanceof Player) {

                Player player = (Player) sender;

                if (utils.homeIsNull(player)) {
                    player.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "*" + ChatColor.DARK_RED + "] " + ChatColor.GRAY + "You must first use /sethome");
                } else {
                    if (config.getBoolean("home-command-delay")) {
                        int coolDown = config.getInt("home-time-delay");
                        if (cooldownTimeHome.containsKey(player)) {
                            player.sendMessage(prefixError + "You must wait for " + ChatColor.RED + cooldownTimeHome.get(player) + ChatColor.GRAY + " seconds.");
                        } else {
                            sendPlayerHome(player);
                            setCoolDownTimeHome(player, coolDown);
                        }
                    } else {
                        sendPlayerHome(player);
                    }
                }
            }
        }

        return true;
    }
    public void onEnable() {
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "AshlynSurvival Initiated! (Build: JA-1.0.0)");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        Bukkit.getPluginManager().registerEvents(new onPlayerJoinLeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerSleepEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerDeathEvent(), this);
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(this.getCommand("coords")).setExecutor(new CoordsCommand());
        Objects.requireNonNull(this.getCommand("bccoords")).setExecutor(new BCCoordsCommand());
        Objects.requireNonNull(this.getCommand("herstart")).setExecutor(new RestartCommand());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
        Objects.requireNonNull(this.getCommand("sethome")).setExecutor(this);
        Objects.requireNonNull(this.getCommand("home")).setExecutor(this);
        Objects.requireNonNull(this.getCommand("heal")).setExecutor(new HealCommand());
        getServer().getPluginManager().registerEvents(new SetHomeEvent(this), this);

        config.options().copyDefaults(true);
        saveDefaultConfig();

        try {
            config.save(getDataFolder() + File.separator + "config.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!file.exists()) {
            saveHomesFile();
        }
        cooldownTimeHome = new HashMap<>();
        cooldownTaskHome = new HashMap<>();

        cooldownTimeSetHome = new HashMap<>();
        cooldownTaskSetHome = new HashMap<>();
    }

    public void saveHomesFile() {
        try {
            homes.save(file);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not save homes file.\nHere is the stack trace:");
            e.printStackTrace();
        }
    }

    void sendPlayerHome(Player player) {
        utils.sendHome(player);
        if (config.getBoolean("play-warp-sound")) {
            player.playSound(utils.getHomeLocation(player), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }
        String strFormatted = config.getString("teleport-message").replace("%player%", player.getDisplayName());
        if (config.getBoolean("show-teleport-message")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', strFormatted));
        }
    }

    void setPlayerHome(Player player) {
        utils.setHome(player);
        if (config.getBoolean("show-sethome-message")) {
            String strFormatted = config.getString("sethome-message").replace("%player%", player.getDisplayName());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', strFormatted));
        }
    }

    void setCoolDownTimeHome(Player player, int coolDown) {
        cooldownTimeHome.put(player, coolDown);
        cooldownTaskHome.put(player, new BukkitRunnable() {
            public void run() {
                cooldownTimeHome.put(player, cooldownTimeHome.get(player) - 1);
                if (cooldownTimeHome.get(player) == 0) {
                    cooldownTimeHome.remove(player);
                    cooldownTaskHome.remove(player);
                    cancel();
                }
            }
        });
        cooldownTaskHome.get(player).runTaskTimer(this, 20, 20);
    }
    void setCoolDownTimeSetHome(Player player, int coolDown) {
        cooldownTimeSetHome.put(player, coolDown);
        cooldownTaskSetHome.put(player, new BukkitRunnable() {
            public void run() {
                cooldownTimeSetHome.put(player, cooldownTimeSetHome.get(player) - 1);
                if (cooldownTimeSetHome.get(player) == 0) {
                    cooldownTimeSetHome.remove(player);
                    cooldownTaskSetHome.remove(player);
                    cancel();
                }
            }
        });
        cooldownTaskSetHome.get(player).runTaskTimer(this, 20, 20);
    }
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
