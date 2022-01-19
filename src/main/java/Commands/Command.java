package Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Command {

    public void run(GuildMessageReceivedEvent event);

}
