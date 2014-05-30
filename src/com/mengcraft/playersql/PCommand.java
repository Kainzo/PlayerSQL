package com.mengcraft.playersql;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("playersql.admin")) {
            sender.sendMessage(PTrans.h);
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage("/player save-all");
            return true;
        }
        if (args.length < 2) {
            if (args[0].equals("save-all")) {
                OnCommandThread thread = new OnCommandThread(sender);
                thread.start();
                return true;
            }
            else {
                sender.sendMessage("/player save-all");
                return true;
            }
        }
        else {
            sender.sendMessage("/player save-all");
            return true;
        }
    }

    class OnCommandThread extends Thread
	{
		private CommandSender sender;

		public OnCommandThread(CommandSender commandSender)
		{
			sender = commandSender;
		}

		@Override
		public void run()
		{
			if (PUtils.saveAllPlayer()) {
				sender.sendMessage(ChatColor.GREEN + PTrans.a);
			}
		}
	}

}
