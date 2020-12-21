package events;

import Dbot.Bot;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.xml.crypto.Data;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UserInfoEmbedCommand extends ListenerAdapter {

    public UserInfoEmbedCommand() {
        Bot.commands.put("!user", "Shiko info rreth nje Member");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String regex = "!user \\w(.){0,20}";


        String  message =  event.getMessage().getContentRaw();

        if(message.equals("!user")) {

            event.getChannel().sendMessage("Per te krijuar nje embed duhet te japesh usernamin, provo !user username").queue();

        }else if(message.matches(regex)){

            String effectiveName = message.substring(message.indexOf(" ") + 1);
            User user = event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0).getUser();
            //creating Embed
            EmbedBuilder avatarEmbed = new EmbedBuilder();
            avatarEmbed.setTitle(user.getName() + "'s" + " Information");
            avatarEmbed.setColor(Color.CYAN);
            avatarEmbed.addField("Name", user.getName(),true);
            avatarEmbed.addField("Online status", event.getGuild().getMember(user).getOnlineStatus().toString(),true);
            avatarEmbed.addField("Join Date", event.getGuild().getMember(user).getJoinDate().format(dateTimeFormatter),true);
            avatarEmbed.addField("Permissions", event.getGuild().getMember(user).getPermissions().toString(), true);
            avatarEmbed.addField("Roles",event.getGuild().getMember(user).getRoles().toString(), false);
            avatarEmbed.addField("Avatar" , "" , false);

            avatarEmbed.setImage(user.getAvatarUrl());

            event.getChannel().sendMessage(avatarEmbed.build()).queue();

        }
    }
}
