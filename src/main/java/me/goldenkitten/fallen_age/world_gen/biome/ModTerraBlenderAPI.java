package me.goldenkitten.fallen_age.world_gen.biome;

import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.world_gen.biome.custom.ModOverworldRegion;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlenderAPI {
    public static void registerRegions() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(FallenAge.MOD_ID, "overworld"), 1000));
    }
}