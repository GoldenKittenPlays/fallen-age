package me.goldenkitten.fallen_age.data_gen;

import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.block.ModBlocks;
import me.goldenkitten.fallen_age.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> future,
                               CompletableFuture<TagLookup<Block>> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, future, completableFuture, FallenAge.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.DESOLATE_HOPE_RECORD.get())
                .add(ModItems.DESOLATE_ECHOES_RECORD.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.DRY_LOG.get().asItem());
    }

    @Override
    public @NotNull String getName() {
        return "Item Tags";
    }
}
