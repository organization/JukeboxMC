package org.jukeboxmc.command.internal

import com.nukkitx.protocol.bedrock.data.command.CommandParamType
import org.apache.commons.lang3.math.NumberUtils
import org.jukeboxmc.command.Command
import org.jukeboxmc.command.CommandData
import org.jukeboxmc.command.CommandParameter
import org.jukeboxmc.command.CommandSender
import org.jukeboxmc.command.annotation.Description
import org.jukeboxmc.command.annotation.Name
import org.jukeboxmc.command.annotation.Permission
import org.jukeboxmc.player.Player
import org.jukeboxmc.world.gamerule.GameRule

/**
 * @author LucGamesYT
 * @version 1.0
 */
@Name("time")
@Description("Set the crurrent world time.")
@Permission("jukeboxmc.command.time")
class TimeCommand : Command(
    CommandData.builder()
        .setParameters(
            arrayOf(
                CommandParameter("start", mutableListOf("start", "stop"), false),
            ),
            arrayOf(
                CommandParameter("set", mutableListOf("set"), false),
                CommandParameter("time", mutableListOf("sunrise", "day", "noon", "sunset", "night", "midnight"), false),
            ),
            arrayOf(
                CommandParameter("set", mutableListOf("set"), false),
                CommandParameter("time", CommandParamType.INT, false),
            ),
        ).build(),
) {
    override fun execute(commandSender: CommandSender, command: String, args: Array<String>) {
        if (commandSender is Player) {
            if (args.size == 1) {
                if (args[0].equals("start", ignoreCase = true)) {
                    if (!commandSender.world!!.getGameRule<Boolean>(GameRule.DO_DAYLIGHT_CYCLE)) {
                        commandSender.world!!.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true)
                        commandSender.sendMessage("Time started.")
                    } else {
                        commandSender.sendMessage("§cThe time is already started.")
                    }
                } else if (args[0].equals("stop", ignoreCase = true)) {
                    if (commandSender.world!!.getGameRule(GameRule.DO_DAYLIGHT_CYCLE)) {
                        commandSender.world!!.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
                        commandSender.sendMessage("Time stopped.")
                    } else {
                        commandSender.sendMessage("§cThe time is already started.")
                    }
                }
            } else if (args.size == 2) {
                if (args[0].equals("set", ignoreCase = true)) {
                    if (NumberUtils.isCreatable(args[1])) {
                        val time = args[1].toInt()
                        commandSender.world?.setWorldTime(time)
                        commandSender.sendMessage("Set the time to $time")
                    } else {
                        when (args[1]) {
                            "sunrise" -> {
                                commandSender.world?.setWorldTime(23000)
                                commandSender.sendMessage("Set the time to 23000")
                            }

                            "day" -> {
                                commandSender.world?.setWorldTime(1000)
                                commandSender.sendMessage("Set the time to 1000")
                            }

                            "noon" -> {
                                commandSender.world?.setWorldTime(6000)
                                commandSender.sendMessage("Set the time to 6000")
                            }

                            "sunset" -> {
                                commandSender.world?.setWorldTime(12000)
                                commandSender.sendMessage("Set the time to 12000")
                            }

                            "night" -> {
                                commandSender.world?.setWorldTime(13000)
                                commandSender.sendMessage("Set the time to 13000")
                            }

                            "midnight" -> {
                                commandSender.world?.setWorldTime(18000)
                                commandSender.sendMessage("Set the time to 18000")
                            }

                            else -> {}
                        }
                    }
                }
            }
        } else {
            commandSender.sendMessage("§cYou must be a player")
        }
    }
}
