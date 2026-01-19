package fr.hydroxios.twitchInteractive.manager;

import fr.hydroxios.twitchInteractive.command.*;
import fr.hydroxios.twitchInteractive.command.ICommand;
import fr.hydroxios.twitchInteractive.utils.Manager;

public class CommandManager extends Manager<ICommand> {

    public CommandManager() {
        this.add(new CommandHelp());
        this.add(new CommandReload());
        this.add(new CommandToken());
        this.add(new CommandDisconnect());
        this.add(new CommandChannel());
        this.add(new CommandStatus());
    }

}
