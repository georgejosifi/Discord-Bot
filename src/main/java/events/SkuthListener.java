package events;

import Dbot.Bot;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SkuthListener extends ListenerAdapter {
    @Override
    public void onGuildVoiceJoin(@NotNull GuildVoiceJoinEvent event) {
        if(event.getMember().getOnlineStatus().toString().equalsIgnoreCase("offline")){
            Bot.sendMessageToDefChannel(event.getGuild(),"O " + event.getMember().getAsMention() + " o super skuth o viktim mos rri invisible si kurv!");
            event.getGuild().kickVoiceMember(event.getMember()).complete();
            Bot.sendInstructionToChannel(event.getGuild().getDefaultChannel(),"Ndrro statusin neqoftese do te besh join skuth!");
        }
    }
}
