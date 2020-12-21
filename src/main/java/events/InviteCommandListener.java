package events;

import Dbot.Bot;
import net.dv8tion.jda.core.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class InviteCommandListener extends ListenerAdapter {
    public InviteCommandListener() {
        Bot.commands.put("!invite", "Krijo nje invite Link qe zgjat 1 ore");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        String sender = event.getMember().getAsMention();
        String link = event.getChannel().createInvite().setMaxAge(3600).complete().getURL();

        if(messageSent.equals("!invite")) {
            event.getChannel().sendMessage("Hey " + sender + " ja ku e ke  invite linkun" + link ).queue();
        }
    }
}
