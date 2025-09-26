package me.goldenkitten.fallen_age.world_gen.biome;

import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.sound.ModSounds;
import me.goldenkitten.fallen_age.world_gen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> DESOLATE_BIOME = register("desolate_biome");
    public static final ResourceKey<Biome> HOPE_BIOME = register("hope_biome");

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(DESOLATE_BIOME, desolateBiome(context));
        context.register(HOPE_BIOME, hopeBiome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        //BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        //BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        //BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        //BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        //BiomeDefaultFeatures.addDefaultSprings(builder);
        //BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome hopeBiome(BootstapContext<Biome> context) {
        //MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.ALEXANDRITE_GEODE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRY_PLACED_KEY);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC0D8FF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x77ADFF)
                .grassColorOverride(0x7f03fc)
                .foliageColorOverride(0xd203fc)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(ModSounds.DESOLATE_HOPE.getHolder().get()))
                .build();
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(effects)
                .build();
    }

    public static Biome desolateBiome(BootstapContext<Biome> context) {
        //MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        //BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        //BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModPlacedFeatures.ALEXANDRITE_GEODE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DRY_PLACED_KEY);
        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0xC0D8FF)
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .skyColor(0x77ADFF)
                .grassColorOverride(0x7f03fc)
                .foliageColorOverride(0xd203fc)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(ModSounds.DESOLATE_ECHOES.getHolder().get()))
                .build();
        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(new MobSpawnSettings.Builder().build())
                .specialEffects(effects)
                .build();
    }

    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(FallenAge.MOD_ID, name));
    }
}
