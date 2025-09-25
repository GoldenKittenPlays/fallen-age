package me.goldenkitten.fallen_age.world_gen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.goldenkitten.fallen_age.world_gen.tree.ModFoliagePlacerTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class DryFoliagePlacer extends FoliagePlacer {
    public static final Codec<DryFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance)
            .and(Codec.intRange(0, 16).fieldOf("height").forGetter(fp -> fp.height)).apply(instance, DryFoliagePlacer::new));
    protected final int height;

    public DryFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.DRY_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader pLevel, @NotNull FoliageSetter foliageSetter, @NotNull RandomSource pRandom, @NotNull TreeConfiguration pConfig, int maxFreeTreeHeight,
                                 FoliagePlacer.@NotNull FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        // Creating the foliage
        // attachment.pos() is the first position ABOVE the last places log

        // tryPlaceLeaf() // places one leave at given position!
        for(int i = 0; i < 4; i++) {
            this.placeLeavesRow(pLevel, foliageSetter, pRandom, pConfig, attachment.pos().above(i), 2, i + 1, attachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}