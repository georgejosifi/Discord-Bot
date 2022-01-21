package Dbot;

import Utilities.MuteProcess;
import events.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;


import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public  class Bot {
    public static HashMap<String,String> commands = new HashMap<>();

    public static void main(String[] args) throws LoginException {
        GatewayIntent [] intents = {GatewayIntent.DIRECT_MESSAGES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_VOICE_STATES};
        JDA jda = JDABuilder.createDefault(System.getenv("BotToken"),Arrays.asList(intents)).setChunkingFilter(ChunkingFilter.ALL).build();


        jda.addEventListener(new HelloEventListener());
        jda.addEventListener(new CommandListener());
        jda.addEventListener(new XhoiListener());
        jda.addEventListener(new PrivateMessageListener());
        jda.addEventListener(new VoiceChannelListener());
        jda.addEventListener(new ElvisGreetingAdapter());
        jda.addEventListener(new SkuthListener());

        


    }

    public static void sendPM(User user, String message) {
        // switch queue() to complete() in order to throw exception to tell if someone blocked the bot
              user.openPrivateChannel().complete().sendMessage(message).complete();
    }

    public static void sendPM(User user , MessageEmbed embed) {
        user.openPrivateChannel().complete().sendMessage(embed).queue();
    }

    public static void sendInstructionToChannel(TextChannel channel,String message) {
        channel.sendMessage(new EmbedBuilder().setDescription(message).setColor(Color.GREEN).build()).queue();
    }
    public static void sendWarningToChannel(TextChannel channel, String message) {
        channel.sendMessage(new EmbedBuilder().setDescription(message).setColor(Color.RED).build()).queue();
    }
    public static void sendInformationToChannel(TextChannel channel, String message) {
        channel.sendMessage(new EmbedBuilder().setDescription(message).setColor(Color.YELLOW).build()).queue();
    }

    public static void sendEmbedPMToDefChannel(Guild guild, String messageEmbed) {
        guild.getDefaultChannel().sendMessage(new EmbedBuilder().setDescription(messageEmbed).setColor(Color.yellow).build()).queue();
    }

    public static void sendMessageToDefChannel(Guild guild, String message) {
        guild.getDefaultChannel().sendMessage(message).queue();
    }
}
