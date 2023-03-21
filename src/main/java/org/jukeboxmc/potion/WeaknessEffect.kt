package org.jukeboxmc.potion

import java.awt.Color
import org.jukeboxmc.entity.EntityLiving

/**
 * @author LucGamesYT
 * @version 1.0
 */
class WeaknessEffect : Effect() {
    override val id: Int
        get() = 18
    override val effectType: EffectType
        get() = EffectType.WEAKNESS
    override val effectColor: Color
        get() = Color(72, 77, 72)

    override fun apply(entityLiving: EntityLiving) {}
    override fun update(currentTick: Long) {}
    override fun remove(entityLiving: EntityLiving) {}
}
