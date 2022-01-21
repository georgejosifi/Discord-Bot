package Commands;

import Dbot.Bot;
import Utilities.MuteProcess;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SpamCommand implements Command {


    public void run(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.matches("!spam \\w{0,25}")) {
            String elvisId = "696729111950917674";
            String effectiveName = message.substring(message.indexOf(" ") + 1);
            User userToSpam;
            Member member;

            try {
                 userToSpam = event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0).getUser();
                 member = event.getGuild().getMembersByEffectiveName(effectiveName,true).get(0);
            }catch(Exception e) {
                Bot.sendWarningToChannel(event.getChannel(),"Ska member me emrin " + effectiveName);
                return;
            }

            if(userToSpam.getId().equalsIgnoreCase(elvisId)) {
                for(int i = 0; i < 10; i ++) {
                    Bot.sendPM(userToSpam,"Un jam ti dhe ti je un!");
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    try {
                        Bot.sendPM(userToSpam, "O viktim!");
                    } catch (ErrorResponseException e) {
                        MuteProcess.tempMute(member, event.getChannel());
                        break;
                    }
                }
            }
        }
    }
}
