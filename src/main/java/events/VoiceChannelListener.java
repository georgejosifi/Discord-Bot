package events;

import Dbot.Bot;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class VoiceChannelListener extends ListenerAdapter {

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
       String channel = event.getChannelJoined().getName();
       String name = event.getMember().getEffectiveName();
       String message = name + " u fut ne voice channel: " + channel;
        Bot.sendMessageToDefChannel(event.getGuild(),message);
    }

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        String channelName = event.getChannelLeft().getName();
        String name = event.getMember().getEffectiveName();
        String message = name + " u largua nga voice channel: " + channelName;
        Bot.sendMessageToDefChannel(event.getGuild(),message);
    }

    @Override
    public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {
        Member member = event.getMember();
        String name = member.getEffectiveName();
        String message = name + " beri mute veten";

        if(member.getVoiceState().isMuted()) {
            Bot.sendMessageToDefChannel(event.getGuild(), message);
        }

    }
}
