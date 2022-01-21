package Commands;

import Dbot.Bot;
import Utilities.Poll;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

public class VoteCommand implements Command {
    public static HashMap<Guild, Poll> guildPollHashMap = new HashMap<>();


    public void run(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String createPollRegex = "!vote create (\\w[\\w\\s]*-)+\\w[\\w\\s]*";
        String voteRegex = "!vote v \\d";


        if(message.equals("!vote")) {
            event.getChannel().sendMessage(createVoteEmbed()).queue();
        }else if(message.matches(createPollRegex)) {
            String [] data  = createDataArray(message);
            Poll poll = new Poll();
            poll.createPoll(data,event);
        }else if(message.toString().matches(voteRegex)) {
            if(!guildPollHashMap.containsKey(event.getGuild())) {
                Bot.sendWarningToChannel(event.getChannel(), "Nuk ka asnje Votim te hapur!");
                return;
            }
            Integer vote = Integer.parseInt(message.substring(message.lastIndexOf(" ")).trim());
            Poll jackPoll = guildPollHashMap.get(event.getGuild());
            jackPoll.countVote(event,vote);
        }else if(message.equals("!vote stats")) {
            if(!guildPollHashMap.containsKey(event.getGuild())) {
                Bot.sendWarningToChannel(event.getChannel(), "Nuk ka asnje Votim te hapur!");
                return;
            }
            Poll jackPoll = guildPollHashMap.get(event.getGuild());
            jackPoll.showStats(event);
        }else if(message.equals("!vote close")) {
            if(!guildPollHashMap.containsKey(event.getGuild())) {
                Bot.sendWarningToChannel(event.getChannel(), "Nuk ka asnje Votim te hapur!");
            }
            Poll jackPoll = guildPollHashMap.get(event.getGuild());
            jackPoll.closePoll(event);
        }
    }

    private MessageEmbed createVoteEmbed() {
        EmbedBuilder voteEmbed = new EmbedBuilder();
        voteEmbed.setTitle("Hapat e Votimit");
        voteEmbed.setColor(Color.GREEN);
        voteEmbed.addField("!vote create (question)-(answer1)-(answer2)","Krijo nje Poll",false);
        voteEmbed.addField("!vote v (number)","Voto per nje alternative",false);
        voteEmbed.addField("!vote stats","Shiko statistikat e votimit",false);
        voteEmbed.addField("!vote close","Mbyll votimin",false);

        return voteEmbed.build();

    }

    private String [] createDataArray(String message) {
        String qA = message.substring(13).trim();
        String [] data = qA.split("-");
        data[0] = data[0] + "?";

        return data;
    }
}
