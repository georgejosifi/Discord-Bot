package Commands;

import Dbot.Bot;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class KickCommand implements Command {

    public KickCommand() {
        Bot.commands.put("!kick","Kick nje Member nga serveri");
    }



    public void run(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String regex = "!kick \\w(.){0,20}";
        String vRegex = "!vkick \\w(.){0,20}";
        String regexForTroll = "!kick.*";
        String masterID = System.getenv("DeadshotId");


        if(message.equals("!kick") && event.getAuthor().getId().equals(masterID)) {
            Bot.sendInstructionToChannel(event.getChannel(),"Te lutem shto emrin e tvobekmit qe do besh kick");


        }else if(message.matches(regex) && event.getAuthor().getId().equals(masterID)) {

             String effectiveName = message.substring(message.indexOf(" ") + 1);
             Member memberToKick;
             try {
                  memberToKick = (Member) event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0);
             }catch(Exception e) {
                 Bot.sendWarningToChannel(event.getChannel(),"Ska member me emrin " + effectiveName);
                 return;
             }

             event.getGuild().kick(memberToKick).complete();

             Bot.sendInformationToChannel(event.getChannel(),effectiveName + " u be kick nga serveri");


        }else if(message.matches(vRegex) && event.getMember().hasPermission(Permission.VOICE_MOVE_OTHERS)) {
            String effectiveName = message.substring(message.indexOf(" ") + 1);
            Member member;
            try {
                member = (Member) event.getGuild().getMembersByEffectiveName(effectiveName,true).get(0);
            }catch(Exception e) {
                Bot.sendWarningToChannel(event.getChannel(),"Ska member me emrin " + effectiveName);
                return;
            }

            VoiceChannel channel = null;
            try {
                 channel = event.getGuild().getVoiceChannelsByName("Afk", true).get(0);
            }catch(Exception e) {
                Bot.sendInstructionToChannel(event.getChannel(), "Krijo nje Voice Channel me emrin Afk perpara se te perdoresh kete komande");
                return;
            }
           // event.getGuild().getController().moveVoiceMember(member,channel).complete();
            event.getGuild().moveVoiceMember(member,channel).complete();

        }else if(message.matches(regexForTroll) && !(event.getAuthor().getId().equals(masterID))) {
            String caller = event.getMember().getAsMention();
            event.getChannel().sendMessage("O " + caller + " sben dot kick ti o i vobekt").queue();
        }
    }
}
