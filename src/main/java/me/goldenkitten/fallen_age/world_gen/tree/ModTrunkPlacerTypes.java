package me.goldenkitten.fallen_age.world_gen.tree;

import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.world_gen.tree.custom.DryTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, FallenAge.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<DryTrunkPlacer>> DRY_TRUNK_PLACER =
            TRUNK_PLACERS.register("dry_trunk_placer", () -> new TrunkPlacerType<>(DryTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACERS.register(eventBus);
    }
}