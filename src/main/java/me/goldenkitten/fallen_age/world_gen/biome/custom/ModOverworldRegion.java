package me.goldenkitten.fallen_age.world_gen.biome.custom;

import com.mojang.datafixers.util.Pair;
import me.goldenkitten.fallen_age.Utils;
import me.goldenkitten.fallen_age.world_gen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint,
            ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            for (ResourceKey<Biome> biome : Utils.getOverworldBiomes()) {
                if (!biome.equals(Biomes.MUSHROOM_FIELDS)) {
                    modifiedVanillaOverworldBuilder.replaceBiome(biome, ModBiomes.DESOLATE_BIOME);
                }
                else {
                    modifiedVanillaOverworldBuilder.replaceBiome(biome, ModBiomes.HOPE_BIOME);
                }
            }
        });
    }
}