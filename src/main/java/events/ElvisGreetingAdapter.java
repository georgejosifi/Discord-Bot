package events;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ElvisGreetingAdapter extends ListenerAdapter {
    @Override
    public void onUserUpdateOnlineStatus(@NotNull UserUpdateOnlineStatusEvent event) {
        String elvisId = System.getenv("ElvisId");
        String xhoiId = System.getenv("XhoiId");
        if(event.getMember().getId().equals(elvisId) &&
                event.getNewOnlineStatus().getKey().equalsIgnoreCase("online")) {
            event.getGuild().getDefaultChannel().sendMessage("Mireseerdhe Elvis prototipi 2.0").queue();
        }else if(event.getMember().getId().equals(xhoiId) &&
                event.getNewOnlineStatus().getKey().equalsIgnoreCase("online")) {
            event.getGuild().getDefaultChannel().sendMessage("Uuuuu erdhi Admini!").queue();
        }

    }
}
