package events;

import Dbot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.awt.*;

public class ShowCommandsListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        User user = event.getMember().getUser();

        if(message.equals("!commands")) {
            EmbedBuilder commandsEmbed = new EmbedBuilder();
            commandsEmbed.setTitle("Komandat");
            commandsEmbed.setColor(Color.GREEN);
            for(String command : Bot.commands.keySet()){

                String value = Bot.commands.get(command);
                commandsEmbed.addField(command, value,false);

            }
            //event.getChannel().sendMessage(commandsEmbed.build()).queue();
            Bot.sendPM(user,commandsEmbed.build());


        }
    }

}
