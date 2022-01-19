package Utilities;

import Dbot.Bot;
import net.dv8tion.jda.api.entities.*;

import java.util.*;
import java.util.List;

public class MuteProcess {
    public static HashMap<Member, List<Role>> mutedMemberRoleMap = new HashMap<>();


    public static void muteMember (Member member, TextChannel channel) {
         if(mutedMemberRoleMap.containsKey(member)){
             Bot.sendWarningToChannel(channel, member.getEffectiveName() + " eshte bere njehere muted!");
             return;
         }
         mutedMemberRoleMap.put(member,member.getRoles());
         Role muted = RoleFactory.createMuteRole(member.getGuild());
         member.getGuild().modifyMemberRoles(member,member.getGuild().getRolesByName("Muted",true), member.getRoles()).complete();
         Bot.sendInformationToChannel(channel, member.getAsMention() + " u be mute!");
    }

    public static void unmuteMember(Member member, TextChannel channel) {
        if(!mutedMemberRoleMap.containsKey(member)) {
            Bot.sendWarningToChannel(channel,member.getEffectiveName() + " nuk eshte muted o qorr!");
            return;
        }
        System.out.println("hello");
        Role muted = RoleFactory.createMuteRole(member.getGuild());
        member.getGuild().removeRoleFromMember(member, muted).complete();
        member.getGuild().modifyMemberRoles(member, mutedMemberRoleMap.get(member)).complete();
        mutedMemberRoleMap.remove(member);
        Bot.sendInformationToChannel(channel, member.getAsMention() + " u be unmuted.");
    }

      public static void tempMute(Member member, TextChannel channel) {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                MuteProcess.unmuteMember(member,channel);
            }
        };
        Bot.sendInformationToChannel(channel, member.getAsMention() + " cin ban block ti mer viktim! Je mute per 1 minut.");
        MuteProcess.muteMember(member,channel);
        timer.schedule(timerTask,60000);
      }




}
