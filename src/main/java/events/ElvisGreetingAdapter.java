package events;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ElvisGreetingAdapter extends ListenerAdapter {
    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        String elvisId = "696729111950917674";
        String xhoiId = "272139757944045569";
        if(event.getMember().getId().equals(elvisId) &&
                event.getNewOnlineStatus().getKey().equalsIgnoreCase("online")) {
            event.getGuild().getDefaultChannel().sendMessage("Mireseerdhe Elvis prototipi 2.0").queue();
        }else if(event.getMember().getId().equals(xhoiId) &&
                event.getNewOnlineStatus().getKey().equalsIgnoreCase("online")) {
            event.getGuild().getDefaultChannel().sendMessage("Uuuuu erdhi Admini!").queue();
        }

    }
}
