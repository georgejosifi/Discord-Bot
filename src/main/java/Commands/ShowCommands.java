package Commands;

import Dbot.Bot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ShowCommands implements Command {


    public void run(GuildMessageReceivedEvent event) {
        if(Bot.commands.isEmpty()) {
            Bot.commands.put("!user","Shiko info rreth nje Member");
            Bot.commands.put("!invite","Krijo nje invite Link qe zgjat 1 ore");
            Bot.commands.put("!vote","Krijo nje proces Votimi");
            Bot.commands.put("!spam","Spam nje noob");
            Bot.commands.put("!mute","Mute nje noob");
            Bot.commands.put("!unmute","Unmute nje Member");
            Bot.commands.put("!kick","Kick nje Member nga serveri");
        }
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
