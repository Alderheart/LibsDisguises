package me.libraryaddict.disguise.commands;

import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.DisguiseConfig;
import me.libraryaddict.disguise.LibsDisguises;
import me.libraryaddict.disguise.disguisetypes.*;
import me.libraryaddict.disguise.utilities.DisguiseUtilities;
import me.libraryaddict.disguise.utilities.LibsPremium;
import me.libraryaddict.disguise.utilities.UpdateChecker;
import me.libraryaddict.disguise.utilities.params.ParamInfoManager;
import me.libraryaddict.disguise.utilities.parser.DisguisePerm;
import me.libraryaddict.disguise.utilities.parser.DisguisePermissions;
import me.libraryaddict.disguise.utilities.reflection.NmsVersion;
import me.libraryaddict.disguise.utilities.reflection.ReflectionManager;
import me.libraryaddict.disguise.utilities.translations.LibsMsg;
import me.libraryaddict.disguise.utilities.translations.TranslateType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permissible;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class LibsDisguisesCommand implements CommandExecutor, TabCompleter {
    protected ArrayList<String> filterTabs(ArrayList<String> list, String[] origArgs) {
        if (origArgs.length == 0)
            return list;

        Iterator<String> itel = list.iterator();
        String label = origArgs[origArgs.length - 1].toLowerCase();

        while (itel.hasNext()) {
            String name = itel.next();

            if (name.toLowerCase().startsWith(label))
                continue;

            itel.remove();
        }

        return list;
    }

    protected String[] getArgs(String[] args) {
        ArrayList<String> newArgs = new ArrayList<>();

        for (int i = 0; i < args.length - 1; i++) {
            String s = args[i];

            if (s.trim().isEmpty())
                continue;

            newArgs.add(s);
        }

        return newArgs.toArray(new String[0]);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            LibsDisguises disguises = LibsDisguises.getInstance();

            String version = disguises.getDescription().getVersion();

            if (!disguises.isReleaseBuild()) {
                version += "-";

                if (disguises.isNumberedBuild()) {
                    version += "b";
                }

                version += disguises.getBuildNo();
            }

            sender.sendMessage(ChatColor.DARK_GREEN + "This server is running " + "Lib's Disguises v" + version +
                    " by libraryaddict, formerly maintained by Byteflux and NavidK0.");

            if (sender.hasPermission("libsdisguises.reload")) {
                sender.sendMessage(ChatColor.DARK_GREEN + "Use " + ChatColor.GREEN + "/libsdisguises " + "reload" +
                        ChatColor.DARK_GREEN + " to reload the config. All disguises will be blown by doing this" +
                        ".");
            }

            if (sender.hasPermission("libsdisguises.update")) {
                sender.sendMessage(ChatColor.DARK_GREEN + "Use " + ChatColor.GREEN + "/libsdisguises update" +
                        ChatColor.DARK_GREEN +
                        " to update Lib's Disguises to latest jenkins build. This will be updated on server restart. " +
                        "To force an update, use /libsdisguises update! with an ! on the end");
            }

            // TODO Other options

            if (LibsPremium.isPremium()) {
                sender.sendMessage(ChatColor.DARK_GREEN + "This server supports the plugin developer!");
            }
        } else if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("libsdisguises.reload")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                DisguiseConfig.loadConfig();
                sender.sendMessage(LibsMsg.RELOADED_CONFIG.get());
                return true;
            } else if (args[0].equalsIgnoreCase("count")) {
                if (!sender.hasPermission("libsdisguises.count")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                HashMap<DisguiseType, Integer> counts = new HashMap<>();

                for (Set<TargetedDisguise> disguises : DisguiseUtilities.getDisguises().values()) {
                    for (Disguise disguise : disguises) {
                        if (disguise.isPlayerDisguise() && DisguiseConfig.isScoreboardDisguiseNames()) {
                            if (((PlayerDisguise) disguise).hasScoreboardName()) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    Scoreboard board = player.getScoreboard();

                                    if (board.getEntryTeam(((PlayerDisguise) disguise).getProfileName()) == null) {
                                        DisguiseUtilities.getLogger().warning(
                                                "The player disguise " + ((PlayerDisguise) disguise).getName() +
                                                        " is missing a scoreboard team on " + player.getName() +
                                                        " and possibly more players!");

                                        break;
                                    }
                                }
                            }
                        }

                        counts.compute(disguise.getType(), (a, b) -> (b != null ? b : 0) + 1);
                    }
                }

                if (counts.isEmpty()) {
                    sender.sendMessage(LibsMsg.NO_DISGUISES_IN_USE.get());
                } else {
                    sender.sendMessage(
                            LibsMsg.ACTIVE_DISGUISES_COUNT.get(counts.values().stream().reduce(Integer::sum)));

                    ArrayList<DisguiseType> types = new ArrayList<>(counts.keySet());
                    types.sort((d1, d2) -> String.CASE_INSENSITIVE_ORDER
                            .compare(TranslateType.DISGUISES.get(d1.toReadable()),
                                    TranslateType.DISGUISES.get(d2.toReadable())));

                    StringBuilder builder = new StringBuilder();

                    for (int i = 0; i < types.size(); i++) {
                        builder.append(LibsMsg.ACTIVE_DISGUISES_DISGUISE
                                .get(TranslateType.DISGUISES.get(types.get(i).toReadable()), counts.get(types.get(i))));

                        if (i + 1 < types.size()) {
                            builder.append(LibsMsg.ACTIVE_DISGUISES_SEPERATOR.get());
                        }
                    }

                    sender.sendMessage(LibsMsg.ACTIVE_DISGUISES.get(builder.toString()));
                }
            } else if (args[0].equalsIgnoreCase("mods")) {
                if (!sender.hasPermission("libsdisguises.mods")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                if (!Bukkit.getMessenger().isOutgoingChannelRegistered(LibsDisguises.getInstance(), "fml:handshake")) {
                    sender.sendMessage(LibsMsg.NO_MODS_LISTENING.get());
                    return true;
                }

                Player player;

                if (args.length > 1) {
                    player = Bukkit.getPlayer(args[1]);

                    if (player == null) {
                        sender.sendMessage(LibsMsg.CANNOT_FIND_PLAYER.get(args[1]));
                        return true;
                    }
                } else if (sender instanceof Player) {
                    player = (Player) sender;
                } else {
                    sender.sendMessage(LibsMsg.NO_CONSOLE.get());
                    return true;
                }

                if (!player.hasMetadata("forge_mods")) {
                    sender.sendMessage(LibsMsg.NO_MODS.get(player.getName()));
                    return true;
                }

                sender.sendMessage(LibsMsg.MODS_LIST.get(player.getName(),
                        StringUtils.join((List<String>) player.getMetadata("forge_mods").get(0).value(), ", ")));
            } else if (args[0].equalsIgnoreCase("scoreboard") || args[0].equalsIgnoreCase("board") ||
                    args[0].equalsIgnoreCase("teams")) {
                if (!sender.hasPermission("libsdisguises.scoreboardtest")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                if (DisguiseConfig.getPushingOption() == DisguiseConfig.DisguisePushing.IGNORE_SCOREBOARD) {
                    sender.sendMessage(LibsMsg.LIBS_SCOREBOARD_DISABLED.get());
                }

                Player player;

                if (args.length > 1) {
                    player = Bukkit.getPlayer(args[1]);

                    if (player == null) {
                        sender.sendMessage(LibsMsg.CANNOT_FIND_PLAYER.get(args[1]));
                        return true;
                    }

                    if (!DisguiseAPI.isDisguised(player)) {
                        sender.sendMessage(LibsMsg.DMODPLAYER_NODISGUISE.get(player.getName()));
                        return true;
                    }
                } else if (sender instanceof Player) {
                    player = (Player) sender;

                    if (!DisguiseAPI.isDisguised(player)) {
                        sender.sendMessage(LibsMsg.NOT_DISGUISED.get());
                        return true;
                    }
                } else {
                    sender.sendMessage(LibsMsg.NO_CONSOLE.get());
                    return true;
                }

                Scoreboard board = player.getScoreboard();

                Team team = board.getEntryTeam(sender.getName());

                if (team == null) {
                    sender.sendMessage(LibsMsg.LIBS_SCOREBOARD_NO_TEAM.get());
                    return true;
                }

                if (team.getOption(Team.Option.COLLISION_RULE) != Team.OptionStatus.NEVER &&
                        team.getOption(Team.Option.COLLISION_RULE) != Team.OptionStatus.FOR_OTHER_TEAMS) {
                    sender.sendMessage(LibsMsg.LIBS_SCOREBOARD_NO_TEAM_PUSH.get(team.getName()));
                    return true;
                }

                sender.sendMessage(LibsMsg.LIBS_SCOREBOARD_SUCCESS.get(team.getName()));
                return true;
            } else if (args[0].equalsIgnoreCase("permtest")) {
                if (!sender.hasPermission("libsdisguises.permtest")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                Permissible player;

                if (args.length > 1) {
                    player = Bukkit.getPlayer(args[1]);

                    if (player == null) {
                        sender.sendMessage(LibsMsg.CANNOT_FIND_PLAYER.get(args[1]));
                        return true;
                    }
                } else {
                    player = sender;
                }

                DisguisePermissions permissions = new DisguisePermissions(player, "disguise");
                sender.sendMessage(LibsMsg.LIBS_PERM_CHECK_INFO_1.get());
                sender.sendMessage(LibsMsg.LIBS_PERM_CHECK_INFO_2.get());

                if (player.hasPermission("libsdisguises.disguise.pig")) {
                    sender.sendMessage(LibsMsg.NORMAL_PERM_CHECK_SUCCESS.get());

                    if (permissions.isAllowedDisguise(new DisguisePerm(DisguiseType.PIG))) {
                        sender.sendMessage(LibsMsg.LIBS_PERM_CHECK_SUCCESS.get());
                    } else {
                        sender.sendMessage(LibsMsg.LIBS_PERM_CHECK_FAIL.get());
                    }
                } else {
                    sender.sendMessage(LibsMsg.NORMAL_PERM_CHECK_FAIL.get());
                }
            } else if (args[0].equalsIgnoreCase("json") || args[0].equalsIgnoreCase("gson") ||
                    args[0].equalsIgnoreCase("item") || args[0].equalsIgnoreCase("parse") ||
                    args[0].equalsIgnoreCase("tostring")) {
                if (!sender.hasPermission("libsdisguises.json")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                if (!(sender instanceof Player)) {
                    sender.sendMessage(LibsMsg.NO_CONSOLE.get());
                    return true;
                }

                ItemStack item = ((Player) sender).getInventory().getItemInMainHand();

                String gson = DisguiseUtilities.getGson().toJson(item);
                String simple = ParamInfoManager.toString(item);

                // item{nbt} amount
                // item amount data {nbt}

                String itemName = ReflectionManager.getItemName(item.getType());
                ArrayList<String> mcArray = new ArrayList<>();

                if (NmsVersion.v1_13.isSupported() && item.hasItemMeta()) {
                    mcArray.add(itemName + DisguiseUtilities.serialize(NbtFactory.fromItemTag(item)));
                } else {
                    mcArray.add(itemName);
                }

                if (item.getAmount() != 1) {
                    mcArray.add(String.valueOf(item.getAmount()));
                }

                if (!NmsVersion.v1_13.isSupported()) {
                    if (item.getDurability() != 0) {
                        mcArray.add(String.valueOf(item.getDurability()));
                    }

                    if (item.hasItemMeta()) {
                        mcArray.add(DisguiseUtilities.serialize(NbtFactory.fromItemTag(item)));
                    }
                }

                String ldItem = StringUtils.join(mcArray, "-");
                String mcItem = StringUtils.join(mcArray, " ");

                sendMessage(sender, LibsMsg.ITEM_SERIALIZED, LibsMsg.ITEM_SERIALIZED_NO_COPY, gson);

                if (!gson.equals(simple) && !ldItem.equals(simple) && !mcItem.equals(simple)) {
                    sendMessage(sender, LibsMsg.ITEM_SIMPLE_STRING, LibsMsg.ITEM_SIMPLE_STRING_NO_COPY, simple);
                }

                sendMessage(sender, LibsMsg.ITEM_SERIALIZED_MC, LibsMsg.ITEM_SERIALIZED_MC_NO_COPY, mcItem);

                if (mcArray.size() > 1) {
                    sendMessage(sender, LibsMsg.ITEM_SERIALIZED_MC, LibsMsg.ITEM_SERIALIZED_MC_NO_COPY, ldItem);
                }
            } else if (args[0].equalsIgnoreCase("config")) {
                if (!sender.hasPermission("libsdisguises.config")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                ArrayList<String> returns = DisguiseConfig
                        .doOutput(LibsDisguises.getInstance().getConfig(), true, true);

                if (returns.isEmpty()) {
                    sender.sendMessage(LibsMsg.USING_DEFAULT_CONFIG.get());
                    return true;
                }

                for (String s : returns) {
                    sender.sendMessage(ChatColor.AQUA + "[LibsDisguises] " + s);
                }
            } else if (args[0].equalsIgnoreCase("metainfo") || args[0].equalsIgnoreCase("meta")) {
                if (!sender.hasPermission("libsdisguises.metainfo")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                if (args.length > 1) {
                    MetaIndex index = MetaIndex.getMetaIndexByName(args[1]);

                    if (index == null) {
                        sender.sendMessage(LibsMsg.META_NOT_FOUND.get());
                        return true;
                    }

                    sender.sendMessage(index.toString());
                } else {
                    ArrayList<String> names = new ArrayList<>();

                    for (MetaIndex index : MetaIndex.values()) {
                        names.add(MetaIndex.getName(index));
                    }

                    names.sort(String::compareToIgnoreCase);

                    if (NmsVersion.v1_13.isSupported()) {
                        ComponentBuilder builder = new ComponentBuilder("").appendLegacy(LibsMsg.META_VALUES.get());

                        Iterator<String> itel = names.iterator();

                        while (itel.hasNext()) {
                            String name = itel.next();

                            builder.appendLegacy(name);
                            builder.event(
                                    new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd.getName() + " metainfo " + name));
                            builder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder("").appendLegacy(LibsMsg.META_CLICK_SHOW.get(name)).create()));

                            if (itel.hasNext()) {
                                builder.appendLegacy(LibsMsg.META_VALUE_SEPERATOR.get());
                            }
                        }

                        sender.spigot().sendMessage(builder.create());
                    } else {
                        sender.sendMessage(LibsMsg.META_VALUES_NO_CLICK
                                .get(StringUtils.join(names, LibsMsg.META_VALUE_SEPERATOR.get())));
                    }
                }
            } else if (args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("update!")) {
                if (!sender.hasPermission("libsdisguises.update")) {
                    sender.sendMessage(LibsMsg.NO_PERM.get());
                    return true;
                }

                UpdateChecker checker = LibsDisguises.getInstance().getUpdateChecker();

                if (checker.isDownloading()) {
                    sender.sendMessage(LibsMsg.UPDATE_IN_PROGRESS.get());
                    return true;
                }

                boolean force = args[0].endsWith("!");

                if (!force) {
                    if (checker.getLatestSnapshot() <= 0) {
                        sender.sendMessage(LibsMsg.UPDATE_NOT_READY.get());
                        return true;
                    }

                    if (checker.getLatestSnapshot() == LibsDisguises.getInstance().getBuildNumber()) {
                        sender.sendMessage(LibsMsg.UPDATE_ON_LATEST.get());
                        return true;
                    }
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        boolean result;

                        if (force) {
                            result = checker.grabLatestSnapshot();
                        } else {
                            result = checker.grabSnapshotBuild();
                        }

                        if (!result) {
                            sender.sendMessage(LibsMsg.UPDATE_FAILED.get());
                            return;
                        }

                        sender.sendMessage(LibsMsg.UPDATE_SUCCESS.get()); // Update success, please restart to update

                        if (sender instanceof Player) {
                            Bukkit.getConsoleSender().sendMessage(LibsMsg.UPDATE_SUCCESS.get());
                        }
                    }
                }.runTaskAsynchronously(LibsDisguises.getInstance());
            } else {
                sender.sendMessage(LibsMsg.LIBS_COMMAND_WRONG_ARG.get());
            }
        }
        return true;
    }

    private void sendMessage(CommandSender sender, LibsMsg prefix, LibsMsg oldVer, String string) {
        if (!NmsVersion.v1_13.isSupported()) {
            sender.sendMessage(oldVer.get(string));
            return;
        }

        int start = 0;
        int msg = 1;

        ComponentBuilder builder = new ComponentBuilder("").appendLegacy(prefix.get());

        while (start < string.length()) {
            int end = Math.min(256, string.length() - start);

            String sub = string.substring(start, start + end);

            builder.append(" ");

            if (string.length() <= 256) {
                builder.appendLegacy(LibsMsg.CLICK_TO_COPY_DATA.get());
            } else {
                builder.reset();
                builder.appendLegacy(LibsMsg.CLICK_COPY.get(msg));
            }

            start += end;

            builder.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sub));
            builder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(LibsMsg.CLICK_TO_COPY_HOVER.get() + (string.length() <= 256 ? "" : " " + msg))
                            .create()));
            msg += 1;
        }

        sender.spigot().sendMessage(builder.create());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] origArgs) {
        ArrayList<String> tabs = new ArrayList<>();
        String[] args = getArgs(origArgs);

        if (args.length == 0)
            tabs.addAll(
                    Arrays.asList("reload", "scoreboard", "permtest", "json", "metainfo", "config", "mods", "update",
                            "count"));

        return filterTabs(tabs, origArgs);
    }
}
