package org.jukeboxmc.world.generator.biomegrid

import org.jukeboxmc.world.Biome

class MapLayerBiome(seed: Long, private val belowLayer: MapLayer) : MapLayer(seed) {
    override fun generateValues(x: Int, z: Int, sizeX: Int, sizeZ: Int): IntArray {
        val values = belowLayer.generateValues(x, z, sizeX, sizeZ)
        val finalValues = IntArray(sizeX * sizeZ)
        for (i in 0 until sizeZ) {
            for (j in 0 until sizeX) {
                var value = values[j + i * sizeX]
                if (value != 0) {
                    setCoordsSeed(x + j, z + i)
                    when (value) {
                        1 -> value = DRY[nextInt(DRY.size)]
                        2 -> value = WARM[nextInt(WARM.size)]
                        3, 1003 -> value = COLD[nextInt(COLD.size)]
                        4 -> value = WET[nextInt(WET.size)]
                        1001 -> value = DRY_LARGE[nextInt(DRY_LARGE.size)]
                        1004 -> value = WET_LARGE[nextInt(WET_LARGE.size)]
                        else -> {}
                    }
                }
                finalValues[j + i * sizeX] = value
            }
        }
        return finalValues
    }

    companion object {
        private val WARM = intArrayOf(
            Biome.DESERT.id,
            Biome.DESERT.id,
            Biome.DESERT.id,
            Biome.SAVANNA.id,
            Biome.SAVANNA.id,
            Biome.PLAINS.id,
        )
        private val WET = intArrayOf(
            Biome.PLAINS.id,
            Biome.PLAINS.id,
            Biome.FOREST.id,
            Biome.BIRCH_FOREST.id,
            Biome.ROOFED_FOREST.id,
            Biome.EXTREME_HILLS.id,
            Biome.SWAMPLAND.id,
        )
        private val DRY = intArrayOf(Biome.PLAINS.id, Biome.FOREST.id, Biome.TAIGA.id, Biome.EXTREME_HILLS.id)
        private val COLD = intArrayOf(Biome.ICE_PLAINS.id, Biome.ICE_PLAINS.id, Biome.COLD_TAIGA.id)

        // private static final int[] WARM_LARGE = new int[]{ Biome.MESA_PLATEAU_STONE.getId(), Biome.MESA_PLATEAU_STONE.getId(), Biome.MESA_PLATEAU.getId() };
        private val DRY_LARGE = intArrayOf(Biome.MEGA_TAIGA.id)
        private val WET_LARGE = intArrayOf(Biome.JUNGLE.id)
    }
}
