package org.jukeboxmc.network.handler

import com.nukkitx.protocol.bedrock.data.EmoteFlag
import com.nukkitx.protocol.bedrock.packet.EmotePacket
import org.jukeboxmc.Server
import org.jukeboxmc.player.Player

/**
 * @author pooooooon
 * @version 1.0
 */
class EmoteHandler : PacketHandler<EmotePacket> {
    override fun handle(packet: EmotePacket, server: Server, player: Player) {
        if (packet.runtimeEntityId != player.entityId) {
            return
        }
        packet.flags.add(EmoteFlag.SERVER_SIDE)
        server.broadcastPacket(packet)
    }
}
