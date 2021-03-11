package Utilities;

import Dbot.Bot;
import com.iwebpp.crypto.TweetNaclFast;
import events.VoteListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Poll {
    public HashMap<Member,Integer> votes = new HashMap<>();
    public Member author;
    public String question;
    public List<String> answers;


    public Poll() {}


    public void createPoll(String [] data, GuildMessageReceivedEvent event) {
        if(VoteListener.guildPollHashMap.containsKey(event.getGuild())) {
            Bot.sendWarningToChannel(event.getChannel(), "Nuk mund te krijosh nje tjeter Votim derisa aktuali te mbyllet.");
            return;
        }

        if(data.length < 3) {
            Bot.sendWarningToChannel(event.getChannel(),"Nuk mund te krijosh nje Votim me nje alternative");
            return;
        }

        StringBuilder sB = new StringBuilder();
        for(int i = 1; i < data.length; i++) {
            sB.append(i + ". " + data[i] + "\n");
        }

        MessageEmbed pollEmbed = createPollEmbed(event.getMember().getEffectiveName(),data[0], sB.toString());

        event.getChannel().sendMessage(pollEmbed).queue();
        this.question = data[0];
        //getting all but the first element which is the question
//        for(int i = 1; i < data.length; i++) {
//            this.answers[i-1] = data[i];
//        }

        this.answers = new ArrayList<>(Arrays.asList(data).subList(1,data.length));
        this.author = event.getMember();

        VoteListener.guildPollHashMap.put(event.getGuild(),this);

    }

    public void countVote(GuildMessageReceivedEvent event, Integer vote) {

        if(vote>this.answers.size() || vote < 1) {
            Bot.sendWarningToChannel(event.getChannel(),"Hapi syte se ska alternative me numer " + vote);
            return;
        }
        if(this.votes.containsKey(event.getMember())) {
            Bot.sendWarningToChannel(event.getChannel(),"Ke votuar njehere o viktim!");
            return;
        }

        this.votes.put(event.getMember(),vote);
        VoteListener.guildPollHashMap.put(event.getGuild(),this);
        event.getMessage().delete().queue();

    }

    public void showStats(GuildMessageReceivedEvent event) {
        StringBuilder sBuilder = new StringBuilder();

        for(int i = 0; i<this.answers.size(); i++) {
            int count = 0;
            for(Integer inter : this.votes.values()) {
                if(inter.intValue() == i+1) {
                    count ++;
                }
            }
            sBuilder.append(i+1 + ". " + this.answers.get(i) + " - " + count + " Vota" + "\n");
        }
       MessageEmbed pollResults = createPollEmbed(this.author.getEffectiveName(),question,sBuilder.toString());
        event.getChannel().sendMessage(pollResults).queue();
    }

    public void closePoll(GuildMessageReceivedEvent event) {
        VoteListener.guildPollHashMap.remove(event.getGuild());
        event.getChannel().sendMessage("Votimi u mbyll.").queue();
    }

    private MessageEmbed createPollEmbed(String authorName, String question, String answer) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Votim i hapur nga " + authorName);
        builder.setColor(Color.GREEN);
        builder.setDescription(question + "\n" + "\n" + answer);
        return builder.build() ;
    }


}
