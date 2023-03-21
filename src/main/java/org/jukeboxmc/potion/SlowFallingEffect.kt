package org.jukeboxmc.potion

import java.awt.Color
import org.jukeboxmc.entity.EntityLiving

/**
 * @author LucGamesYT
 * @version 1.0
 */
class SlowFallingEffect : Effect() {
    override val id: Int
        get() = 25
    override val effectType: EffectType
        get() = EffectType.SLOW_FALLING
    override val effectColor: Color
        get() = Color(206, 255, 255)

    override fun apply(entityLiving: EntityLiving) {}
    override fun update(currentTick: Long) {}
    override fun remove(entityLiving: EntityLiving) {}
}
