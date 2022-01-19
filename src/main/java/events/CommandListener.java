package events;

import Commands.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String commandPart = message;
        if(commandPart.contains(" ")) {
            commandPart = message.substring(0, message.indexOf(" "));  //cut pjesen para te komandes prsh vetem !kick dhe jo !kick deadshot
        }
        Command command;
        switch(commandPart) {
            case "!invite": command = new InviteCommand();
                break;
            case "!kick":   command = new KickCommand();
                break;
            case "!mute":   command = new MuteCommand();
                break;
            case "!unmute": command = new MuteCommand();
                break;
            case "!commands": command = new ShowCommands();
                break;
            case "!spam":   command = new SpamCommand();
                break;
            case "!user":   command = new UserInfoCommand();
                break;
            case "!vote":   command = new VoteCommand();
                break;
            default:   command = null;
                break;
        }
        if(command!=null) {
            command.run(event);
        }

    }
}
