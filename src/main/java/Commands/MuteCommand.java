package Commands;

import Dbot.Bot;
import Utilities.MuteProcess;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MuteCommand implements Command {

    public void run(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String effectiveName = message.substring(message.indexOf(" ") + 1);

        if(message.matches("!mute \\w{0,25}")) {
            Member member;
            try {
                member = (Member) event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0);
            }catch(Exception e) {
                Bot.sendWarningToChannel(event.getChannel(),"Ska member me emrin " + effectiveName);
                return;
            }
            MuteProcess.muteMember(member,event.getChannel());
            //MuteProcess.tempMute(member);
        }else if(message.matches("!unmute \\w{0,25}")) {
            Member member;
            try {
                member = (Member) event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0);
            }catch(Exception e) {
                Bot.sendWarningToChannel(event.getChannel(),"Ska member me emrin " + effectiveName);
                return;
            }
            MuteProcess.unmuteMember(member,event.getChannel());
        }
    }
}
