package Dbot;

import events.*;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.HashMap;

public  class Bot {
    public static HashMap<String,String> commands = new HashMap<>();

    public static void main(String[] args) throws LoginException {

        JDA jda = new JDABuilder("NzY5NjE0NjY3NTUwMTYyOTg0.X5Rlgw.IpW2Yhp3odO_WNxgphaso_qtoUI").build();

        jda.addEventListener(new HelloEventListener());
        jda.addEventListener(new InviteCommandListener());
        jda.addEventListener(new UserInfoEmbedCommand());
        jda.addEventListener(new KickEventListener());
        jda.addEventListener(new XhoiListener());
        jda.addEventListener(new ShowCommandsListener());
        jda.addEventListener(new PrivateMessageListener());
        jda.addEventListener(new VoiceChannelListener());
        


    }

    public static void sendPM(User user,String message) {
        user.openPrivateChannel().complete().sendMessage(message).queue();
    }

    public static void sendPM(User user , MessageEmbed embed) {
        user.openPrivateChannel().complete().sendMessage(embed).queue();
    }

    public static void sendMessageToDefChannel(Guild guild, String message) {
        guild.getDefaultChannel().sendMessage(message).queue();
    }
}
