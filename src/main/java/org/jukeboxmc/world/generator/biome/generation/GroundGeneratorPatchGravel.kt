package org.jukeboxmc.world.generator.biome.generation

import org.jukeboxmc.block.Block
import org.jukeboxmc.block.BlockType
import org.jukeboxmc.world.chunk.Chunk
import org.jukeboxmc.world.generator.biome.GroundGenerator
import java.util.Random

/**
 * @author LucGamesYT
 * @version 1.0
 */
class GroundGeneratorPatchGravel : GroundGenerator() {
    override fun generateTerrainColumn(chunk: Chunk, random: Random, chunkX: Int, chunkZ: Int, surfaceNoise: Double) {
        if (surfaceNoise < -1.0 || surfaceNoise > 2.0) {
            topMaterial = Block.create(BlockType.GRAVEL)
            groundMaterial = Block.create(BlockType.GRAVEL)
        } else {
            topMaterial = Block.create(BlockType.GRASS)
            groundMaterial = Block.create(BlockType.DIRT)
        }
        super.generateTerrainColumn(chunk, random, chunkX, chunkZ, surfaceNoise)
    }
}
