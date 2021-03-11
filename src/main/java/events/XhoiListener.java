package events;



import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;

public class XhoiListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.toLowerCase().contains("mcuce")) {
            event.getChannel().sendMessage("Plashii boljeriii 0_0").queue();
        }else if(message.toLowerCase().equals("un jam batmani bitch")) {
            event.getChannel().sendMessage("Vecse skam rrujt sqetllat!").queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setImage("https://media1.tenor.com/images/7114626996e7bcac3a0075dc895aea4c/tenor.gif?itemid=4584215");
            event.getChannel().sendMessage(embed.build()).queue();
        }

    }
}
