package me.libraryaddict.disguise.utilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by libraryaddict on 15/06/2017.
 */
public enum LibsMsg {
    // Format being CLASS_STRING. So no perm = DISG_COMMAND_NO_PERM. Or DISG_PARSE_NO_PERM_OPTION
    DHELP_OPTIONS("%s options: %s"), NO_PERMS_USE_OPTIONS(
            ChatColor.RED + "Ignored %s" + " options you do not have " + "permission to use. Add " + "'show' to view unusable options."),
    DISG_HELP4(ChatColor.DARK_GREEN + "/disguise " + "<Dropped_Item/Falling_Block> <Id> <Durability>"),
    DHELP_CANTFIND(ChatColor.RED + "Cannot find the disguise %s"),
    NO_PERM_DISGUISE(ChatColor.RED + "You do not have permission for " + "that disguise!"),
    DISG_ENT_HELP1(ChatColor.DARK_GREEN + "Choose a disguise then right click an entity to disguise it!"),
    DISG_ENT_HELP2(ChatColor.DARK_GREEN + "You can use the " + "disguises: %s"),
    DISG_ENT_HELP3(ChatColor.DARK_GREEN + "/disguiseentity player <Name>"),
    DISG_ENT_HELP4(ChatColor.DARK_GREEN + "/disguiseentity <DisguiseType> <Baby>"),
    DISG_ENT_HELP5(ChatColor.DARK_GREEN + "/disguiseentity <Dropped_Item/Falling_Block> <Id> " + "<Durability>"),
    DISG_ENT_CLICK(ChatColor.RED + "Right click an entity in the next %s seconds to disguise it as a %s!"),
    DISG_HELP3(ChatColor.DARK_GREEN + "/disguise <DisguiseType> " + "<Baby>"),
    DMODRADIUS(ChatColor.RED + "Successfully modified the disguises of %s" + " entities!"),
    DMODRADIUS_NOENTS(ChatColor.RED + "Couldn't find any disguised entities!"),
    DMODRADIUS_NOPERM(ChatColor.RED + "No " + "permission to modify " + "%s disguises!"),
    DISG_HELP2(ChatColor.DARK_GREEN + "/disguise player " + "<Name>"),
    DISG_HELP1(ChatColor.DARK_GREEN + "Choose a disguise to become the disguise!"),
    CAN_USE_DISGS(ChatColor.DARK_GREEN + "You can use the disguises: %s"),
    DISGUISED(ChatColor.RED + "Now " + "disguised as a %s"),
    DHELP_HELP2(ChatColor.RED + "/disguisehelp %s" + ChatColor.GREEN + " - %s"), DHELP_HELP1(
            ChatColor.RED + "/disguisehelp <DisguiseType>" + " " + ChatColor.GREEN + "- View the options you can set " + "on a disguise. Add 'show' to reveal the options you don't have permission to use"),
    FAILED_DISGIUSE(ChatColor.RED + "Failed to disguise as a %s"), CLONE_HELP1(
            ChatColor.DARK_GREEN + "Right click a entity to get a disguise reference you can pass to other " + "disguise commands!"),
    CLONE_HELP2(
            ChatColor.DARK_GREEN + "Security note: Any references you create will be available to all players " + "able to use disguise references."),
    CLONE_HELP3(
            ChatColor.DARK_GREEN + "/disguiseclone IgnoreEquipment" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")"),
    RELOADED_CONFIG(ChatColor.GREEN + "[LibsDisguises] Reloaded config."),
    DMODIFY_NO_PERM(ChatColor.RED + "No " + "permission to modify your disguise!"),
    DMODIFY_MODIFIED(ChatColor.RED + "Your disguise has been modified!"),
    DMODIFY_HELP1(ChatColor.DARK_GREEN + "Modify your own disguise as you wear " + "it!"),
    DMODIFY_HELP2(ChatColor.DARK_GREEN + "/disguisemodify setBaby true setSprinting true"),
    DMODIFY_HELP3(ChatColor.DARK_GREEN + "You can modify the " + "disguises: %s"), DMODIFYENT_CLICK(
            ChatColor.RED + "Right click a disguised entity " + "in the next %s seconds to modify their disguise!"),
    LIBS_RELOAD_WRONG(ChatColor.RED + "[LibsDisguises] Did you mean 'reload'?"),
    DMODENT_HELP1(ChatColor.DARK_GREEN + "Choose the options for a disguise then right click a entity to modify it!"),
    DMODENT_HELP2(ChatColor.DARK_GREEN + "You can modify the " + "disguises: %s"),
    CANNOT_FIND_PLAYER(ChatColor.RED + "Cannot find the player '%s" + "'"),
    PARSE_USE_SECOND_NUM(ChatColor.RED + "Error! Only the disguises %s" + " and %s" + " uses a second number!"),
    PARSE_NO_PERM_PARAM(
            ChatColor.RED + "Error! You do not have permission to use the parameter %s on the" + " %s " + "disguise!"),
    PARSE_NO_OPTION_VALUE(ChatColor.RED + "No value was given for the option %s"),
    PARSE_OPTION_NA(ChatColor.RED + "Cannot find the option %s"), UPDATE_READY(
            ChatColor.RED + "[LibsDisguises] " + ChatColor.DARK_RED + "There is a update ready to be downloaded! You are using " + ChatColor.RED + "v%s" + ChatColor.DARK_RED + ", the new version is " + ChatColor.RED + "%s" + ChatColor.DARK_RED + "!"),
    PARSE_EXPECTED_RECEIVED(
            ChatColor.RED + "Expected " + ChatColor.GREEN + "%s" + ChatColor.RED + ", received " + ChatColor.GREEN + "%s" + ChatColor.RED + " instead for " + ChatColor.GREEN + "%s"),
    DRADIUS_ENTITIES(ChatColor.DARK_GREEN + "EntityTypes usable are: %s"),
    DRADIUS_UNRECOG(ChatColor.RED + "Unrecognised " + "EntityType %s"),
    PARSE_TOO_MANY_ARGS(ChatColor.RED + "Error! %s doesn't know" + " " + "what to do with %s!"),
    DISG_PLAYER_AS_DISG(ChatColor.RED + "Successfully disguised %s as a %s!"),
    PARSE_NO_PERM_NAME(ChatColor.RED + "Error! You don't have permission to use that name!"),
    DPLAYER_SUPPLY(ChatColor.RED + "You need to supply a disguise as well as " + "the player"),
    PARSE_SUPPLY_PLAYER(ChatColor.RED + "Error! You need " + "to give a player name!"),
    DISG_PLAYER_AS_DISG_FAIL(ChatColor.RED + "Failed to disguise %s as a %s!"), DISABLED_LIVING_TO_MISC(
            ChatColor.RED + "Can't disguise a living entity as a misc disguise. This has been disabled in the" + " config!"),
    DMODRADIUS_HELP1(ChatColor.DARK_GREEN + "Modify the disguises in a radius! Caps at %s blocks!"),
    DMODRADIUS_HELP2(ChatColor.DARK_GREEN + "You can modify the disguises: %s"), DMODRADIUS_HELP3(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> player <Name>")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DMODRADIUS_HELP4(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> <DisguiseType> <Baby" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + ">")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DMODRADIUS_HELP5(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> <Dropped_Item/Falling_Block> <Id> <Durability" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + ">")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DMODRADIUS_HELP6(
            ChatColor.DARK_GREEN + "See the EntityType's usable by " + ChatColor.GREEN + "/disguiseradius " + "EntityTypes"),
    UND_ENTITY(ChatColor.RED + "Right click a disguised entity to " + "undisguise them!"),
    UNDISG_PLAYER(ChatColor.RED + "%s is no longer disguised"),
    LISTEN_ENTITY_PLAYER_DISG_PLAYER(ChatColor.RED + "Disguised the player %s as the player %s!"),
    LISTEN_ENTITY_PLAYER_DISG_ENTITY(ChatColor.RED + "Disguised the player %s as a %s!"),
    LISTEN_ENTITY_ENTITY_DISG_PLAYER(ChatColor.RED + "Disguised s %s as the player %s!"),
    LISTEN_ENTITY_ENTITY_DISG_ENTITY(ChatColor.RED + "Disguised s %s as a %s!"),
    LISTEN_ENTITY_PLAYER_DISG_PLAYER_FAIL(ChatColor.RED + "Failed to disguise the player %s as the player %s!"),
    LISTEN_ENTITY_PLAYER_DISG_ENTITY_FAIL(ChatColor.RED + "Failed to disguise the player %s as a %s!"),
    LISTEN_ENTITY_ENTITY_DISG_PLAYER_FAIL(ChatColor.RED + "Failed to disguise s %s as the player %s!"),
    LISTEN_ENTITY_ENTITY_DISG_ENTITY_FAIL(ChatColor.RED + "Failed to disguise s %s as a %s!"),
    LISTEN_UNDISG_PLAYER(ChatColor.RED + "Undisguised %s"), LISTEN_UNDISG_ENT(ChatColor.RED + "Undisguised the %s"),
    LISTEN_UNDISG_PLAYER_FAIL(ChatColor.RED + "The %s isn't disguised!"),
    MADE_REF(ChatColor.RED + "Constructed a %s disguise! Your reference is %s"),
    MADE_REF_EXAMPLE(ChatColor.RED + "Example usage: /disguise %s"), REF_TOO_MANY(
            ChatColor.RED + "Failed to store the reference, too many cloned disguises. Please raise the " + "maximum " + "cloned disguises, or lower the time they last"),
    LISTEN_UNDISG_ENT_FAIL(ChatColor.RED + "%s isn't disguised!"),
    UNDISG_PLAYER_FAIL(ChatColor.RED + "%s not disguised!"),
    UNDISG_PLAYER_HELP(ChatColor.RED + "/undisguiseplayer <Name>"),
    DMODPLAYER_NODISGUISE(ChatColor.RED + "The " + "player '%s' is " + "not disguised"),
    DMODPLAYER_NOPERM(ChatColor.RED + "You do not have permission to modify this " + "disguise"),
    DMODPLAYER_MODIFIED(ChatColor.RED + "Modified the disguise of %s!"),
    LISTENER_MODIFIED_DISG(ChatColor.RED + "Modified the disguise!"),
    DMODPLAYER_HELP1(ChatColor.DARK_GREEN + "Modify the disguise of another player!"),
    DMODPLAYER_HELP2(ChatColor.DARK_GREEN + "You can modify the " + "disguises: %s"),
    NO_PERM(ChatColor.RED + "You are forbidden to use this command."),
    NOT_DISGUISED(ChatColor.RED + "You are not disguised!"),
    DRADIUS_HELP1(ChatColor.DARK_GREEN + "Disguise all entities in a radius! Caps at %s blocks!"),
    DRADIUS_HELP2(ChatColor.DARK_GREEN + "You can use the " + "disguises: %s"), DRADIUS_HELP3(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> player <Name>")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DRADIUS_HELP4(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> <DisguiseType> <Baby" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + ">")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DRADIUS_HELP5(
            (ChatColor.DARK_GREEN + "/disguiseradius <EntityType" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + "> <Radius> <Dropped_Item/Falling_Block> <Id> <Durability" + ChatColor.DARK_GREEN + "(" + ChatColor.GREEN + "Optional" + ChatColor.DARK_GREEN + ")" + ">")
                    .replace("<", "<" + ChatColor.GREEN).replace(">", ChatColor.DARK_GREEN + ">")), DRADIUS_HELP6(
            ChatColor.DARK_GREEN + "See the EntityType's usable by " + ChatColor.GREEN + "/disguiseradius " + "EntityTypes"),
    D_PARSE_NOPERM(ChatColor.RED + "You do not have permission to use the option %s"),
    PARSE_NO_ARGS("No arguments defined"),
    PARSE_NO_REF(ChatColor.RED + "Cannot find a disguise under the reference " + "%s"),
    PARSE_NO_PERM_REF(ChatColor.RED + "You do not have perimssion to use disguise references!"), PARSE_DISG_NO_EXIST(
            ChatColor.RED + "Error! The disguise " + ChatColor.GREEN + "%s" + ChatColor.RED + " " + "doesn't exist!"),
    PARSE_CANT_DISG_UNKNOWN(ChatColor.RED + "Error! You cannot disguise as " + ChatColor.GREEN + "Unknown!"),
    PARSE_CANT_LOAD(ChatColor.RED + "Error! This disguise " + "couldn't be loaded!"),
    D_HELP1(ChatColor.DARK_GREEN + "Disguise another player!"),
    D_HELP2(ChatColor.DARK_GREEN + "You can use the " + "disguises: %s"),
    DMODRADIUS_USABLE(ChatColor.DARK_GREEN + "EntityTypes usable " + "are: %s" + ChatColor.DARK_GREEN + "."),
    D_HELP3(ChatColor.DARK_GREEN + "/disguiseplayer <PlayerName> player " + "<Name>"),
    D_HELP4(ChatColor.DARK_GREEN + "/disguiseplayer <PlayerName> " + "<DisguiseType> <Baby>"),
    D_HELP5(ChatColor.DARK_GREEN + "/disguiseplayer <PlayerName> <Dropped_Item/Falling_Block> <Id> " + "<Durability>"),
    DMODRADIUS_UNRECOGNIZED(ChatColor.RED + "Unrecognised " + "EntityType %s"),
    DMODRADIUS_NEEDOPTIONS(ChatColor.RED + "You need to supply the disguise options as well as the radius"),
    DRADIUS_NEEDOPTIONS(ChatColor.RED + "You need to supply a disguise as well as the radius"),
    DRADIUS_NEEDOPTIONS_ENTITY(ChatColor.RED + "You need to supply a disguise as well as the radius and EntityType"),
    NOT_NUMBER(ChatColor.RED + "Error! %s is not a " + "number"), DRADIUS_MISCDISG(
            ChatColor.RED + "Failed to disguise %s entities because the option to disguise a living entity " + "as" + " a non-living has been disabled in the config"),
    LIMITED_RADIUS(ChatColor.RED + "Limited radius to %s! Don't want to make too much lag right?"),
    DISRADIUS(ChatColor.RED + "Successfully disguised %s" + " entities!"),
    DISRADIUS_FAIL(ChatColor.RED + "Couldn't find any entities to disguise!"), DMODRADIUS_NEEDOPTIONS_ENTITY(
            ChatColor.RED + "You need to " + "supply" + " the disguise options as well as the radius" + " and EntityType"),
    NO_CONSOLE(ChatColor.RED + "You may not use this command from the console!"), CLICK_TIMER(
            ChatColor.RED + "Right click a entity in the next %s" + " " + "seconds " + "to " + "grab " + "the disguise reference!"),
    UNDISRADIUS(ChatColor.RED + "Successfully undisguised %s entities!"),
    UNDISG(ChatColor.RED + "You are no longer disguised"), UNDISG_FAIL(ChatColor.RED + "You are not disguised!"),
    VIEW_SELF_ON(ChatColor.GREEN + "Toggled viewing own disguise off!"),
    BLOWN_DISGUISE(ChatColor.RED + "Your disguise" + " was blown!"),
    VIEW_SELF_OFF(ChatColor.GREEN + "Toggled viewing own disguise on!"), INVALID_CLONE(
            ChatColor.DARK_RED + "Unknown " + "option '%s" + "' - Valid options are 'IgnoreEquipment' 'DoSneakSprint' 'DoSneak' 'DoSprint'");

    static {
        for (LibsMsg msg : values()) {
            for (LibsMsg msg1 : values()) {
                if (msg == msg1)
                    continue;

                if (!msg.getRaw().equalsIgnoreCase(msg1.getRaw()))
                    continue;

                System.out.println(msg.name() + " and " + msg1.name() + " conflict");
            }
        }

        System.out.println("Alright dont forget to delete this libraryaddict!!!!!!!!!!!!!!!!!!");
    }

    private String string;

    LibsMsg(String string) {
        this.string = string;
    }

    public String getRaw() {
        return string;
    }

    public String get(Object... strings) {
        if (strings.length == 0)
            return TranslateType.MESSAGE.get(getRaw());

        return String.format(TranslateType.MESSAGE.get(getRaw()), (Object[]) strings);
    }

    public String toString() {
        throw new RuntimeException("Dont call this");
    }
}
