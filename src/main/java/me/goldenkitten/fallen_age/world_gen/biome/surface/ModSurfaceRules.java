package me.goldenkitten.fallen_age.world_gen.biome.surface;

import me.goldenkitten.fallen_age.block.ModBlocks;
import me.goldenkitten.fallen_age.world_gen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    //private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    //private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource ALEXANDRITE = makeStateRule(ModBlocks.ALEXANDRITE_BLOCK.get());
    private static final SurfaceRules.RuleSource RAW_ALEXANDRITE = makeStateRule(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
    private static final SurfaceRules.RuleSource CORRUPTION_BLOCK = makeStateRule(ModBlocks.CORRUPTION_BLOCK.get());
    private static final SurfaceRules.RuleSource DESOLATE_SOIL = makeStateRule(ModBlocks.DESOLATE_SOIL.get());

    public static SurfaceRules.RuleSource makeDesolateRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource corruptedSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, CORRUPTION_BLOCK), CORRUPTION_BLOCK);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DESOLATE_BIOME),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CORRUPTION_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CORRUPTION_BLOCK)),
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.HOPE_BIOME),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DESOLATE_SOIL)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, DESOLATE_SOIL)),
                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CORRUPTION_BLOCK)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}