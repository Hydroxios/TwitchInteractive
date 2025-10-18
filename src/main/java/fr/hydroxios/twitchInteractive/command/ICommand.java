package fr.hydroxios.twitchInteractive.command;

import org.bukkit.command.CommandSender;

public interface ICommand {

    String getName();
    boolean execute(CommandSender sender, String[] args);
    
}
