package events;


import Utilities.RoleFactory;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEventListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        String userName = member.getAsMention();
        String  serverName = event.getGuild().getName();
        Role defRole = RoleFactory.createDefaultRole(event.getGuild());
        event.getGuild().addRoleToMember(member,defRole).complete();
        event.getGuild().getDefaultChannel().sendMessage("Mireseerdhe ne " + serverName + " " + userName + " une jam Vis Boti").queue();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();

        if(messageSent.toLowerCase().contains("elvis")){
            String nickName = event.getMember().getEffectiveName();
            String elvisId = System.getenv("ElvisId");

            if(event.getMember().getId().equalsIgnoreCase(elvisId)) {
                event.getChannel().sendMessage("Po " + nickName + " une jam nje Bot si ti, une jam ti. Vecse ti je i uploduar ne nje trup njerezor ndersa un ne Discord!").queue();
                return;
            }
            if(!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("Po " + nickName + " un jam nje Bot").queue();
            }

        }

    }



}
