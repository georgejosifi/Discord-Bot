package events;


import Dbot.Bot;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PrivateMessageListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            event.getChannel().sendMessage("Mos mshkruj privat").queue();
        }
//        if(event.getAuthor().getId().equals("285896194436104192")) {
//            Bot.sendPM(event.getJDA().getUserById("657362531697950720"),event.getMessage().getContentRaw());
//
//        }else if(event.getAuthor().getId().equals("657362531697950720")) {
//            Bot.sendPM(event.getJDA().getUserById("285896194436104192"),event.getMessage().getContentRaw());
//        }
    }
}
