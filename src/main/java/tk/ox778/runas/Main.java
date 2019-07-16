package tk.ox778.runas;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("runas")) {
            return false;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Invalid number of arguments (you must specify player name, and a command to run).");
            return false;
        }

        Player target = getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "There is no player named " + ChatColor.GREEN + args[0] + ChatColor.RED + " on the server.");
            return false;
        }

        String commandToRun = "";
        for (int i = 1; i < args.length; i++) {
            commandToRun += args[i] + " ";
        }

        boolean targetIsOp = target.isOp();

        try
        {
            target.setOp(true);
            getServer().dispatchCommand(target, commandToRun);
        }
        finally
        {
            target.setOp(targetIsOp);
        }

        return true;
    }
}
