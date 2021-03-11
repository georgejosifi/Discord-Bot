package events;

import Dbot.Bot;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class SpamListener extends ListenerAdapter {

    public SpamListener() {
        Bot.commands.put("!spam", "Spam nje noob");
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if(message.matches("!spam \\w{0,25}")) {
            String elvisId = "696729111950917674";
            String effectiveName = message.substring(message.indexOf(" ") + 1);
            User userToSpam;
            try {
                 userToSpam = event.getGuild().getMembersByEffectiveName(effectiveName, true).get(0).getUser();
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
                    Bot.sendPM(userToSpam, "O viktim!");
                }
            }
        }
    }
}
