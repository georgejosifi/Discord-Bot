package events;

import Dbot.Bot;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class KickEventListener extends ListenerAdapter {

    public KickEventListener() {
        Bot.commands.put("!kick","Kick nje Member nga serveri");
    }



    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String regex = "!kick \\w(.){0,20}";
        String vRegex = "!vkick \\w(.){0,20}";
        String regexForTroll = "!kick.*";
        String masterID = "285896194436104192";


        if(message.equals("!kick") && event.getAuthor().getId().equals(masterID)) {
            event.getChannel().sendMessage("Te lutem shto emrin e tvobekmit qe do besh kick").queue();


        }else if(message.matches(regex) && event.getAuthor().getId().equals(masterID)) {

             String effectiveName = message.substring(message.indexOf(" ") + 1);
             Member memberToKick = (Member) event.getGuild().getMembersByEffectiveName(effectiveName,true).get(0);
             event.getGuild().getController().kick(memberToKick).complete();
             event.getChannel().sendMessage(effectiveName + " u be kick nga serveri").queue();


        }else if(message.matches(vRegex) && event.getMember().hasPermission(Permission.VOICE_MOVE_OTHERS)) {
            String effectiveName = message.substring(message.indexOf(" ") + 1);
            Member member = (Member) event.getGuild().getMembersByEffectiveName(effectiveName,true).get(0);
            VoiceChannel channel = event.getGuild().getVoiceChannelsByName("Afk",true).get(0);
            event.getGuild().getController().moveVoiceMember(member,channel).complete();

        }else if(message.matches(regexForTroll) && !(event.getAuthor().getId().equals(masterID))) {
            String caller = event.getMember().getAsMention();
            event.getChannel().sendMessage("O " + caller + " sben dot kick ti o i vobekt").queue();
        }
    }
}
