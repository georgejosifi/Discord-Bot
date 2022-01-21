package Commands;

import Dbot.Bot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class InviteCommand implements Command {



    public void run(GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        String sender = event.getMember().getAsMention();


        if(messageSent.equals("!invite")) {
            String link = event.getChannel().createInvite().setMaxAge(3600).complete().getUrl();
            event.getChannel().sendMessage("Hey " + sender + " ja ku e ke  invite linkun " + link ).queue();
        }
    }
}
