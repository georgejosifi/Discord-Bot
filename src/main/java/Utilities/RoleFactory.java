package Utilities;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RoleFactory {

    public static Role createMuteRole(Guild guild) {
        if(!guild.getRolesByName("Muted",true).isEmpty()) {
            System.out.println("Ekziston ky roli");
            return guild.getRolesByName("Muted",true).get(0);
        }
        Collection<Permission> permissions = new ArrayList<>();
        Collections.addAll(permissions, Permission.MESSAGE_READ,
                Permission.MESSAGE_HISTORY, Permission.VOICE_CONNECT,Permission.VOICE_SPEAK);
        Role muted = guild.createRole().setName("Muted").setPermissions(permissions).setMentionable(true).setColor(Color.orange).complete();
        for(TextChannel channel : guild.getTextChannels()) {
            channel.putPermissionOverride(muted).setDeny(Permission.MESSAGE_WRITE).complete();
        }
        return muted;
    }

    public static Role createDefaultRole(Guild guild) {
        if(!guild.getRolesByName("Default Role",true).isEmpty()) {
            return guild.getRolesByName("Default Role",true).get(0);
        }
       Role defaultRole = (Role) guild.createRole().setName("Default Role").setPermissions(Permission.NICKNAME_CHANGE).setMentionable(true).setColor(Color.gray).complete();
       return defaultRole;
    }
}
