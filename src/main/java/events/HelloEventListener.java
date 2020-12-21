package events;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.events.ShutdownEvent;
import net.dv8tion.jda.core.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.core.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HelloEventListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        String userName = member.getAsMention();
        String  serverName = event.getGuild().getName();
        Role defRole = event.getGuild().getRolesByName("default role",true).get(0);
        event.getGuild().getController().addSingleRoleToMember(member,defRole).complete();
        event.getGuild().getDefaultChannel().sendMessage("Mireseerdhe ne " + serverName + " " + userName + " une jam Vis Boti").queue();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        String nickName = event.getMember().getEffectiveName();

        if(messageSent.toLowerCase().contains("elvis")){

            if(!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("Po " + nickName + " un jam nje Bot").queue();
            }

        }

    }



}
