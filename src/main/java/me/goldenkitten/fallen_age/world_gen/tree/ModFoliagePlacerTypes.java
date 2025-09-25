package me.goldenkitten.fallen_age.world_gen.tree;

import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.world_gen.tree.custom.DryFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, FallenAge.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<DryFoliagePlacer>> DRY_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("dry_foliage_placer", () -> new FoliagePlacerType<>(DryFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
