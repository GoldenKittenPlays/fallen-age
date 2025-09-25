package me.goldenkitten.fallen_age.block.custom;

import me.goldenkitten.fallen_age.Utils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.jetbrains.annotations.NotNull;

public class CorruptionBlock extends Block {
    public CorruptionBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.SCULK).randomTicks().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BIT)
                .requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.FUNGUS));
    }

    @Override
    public void animateTick(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        // Very slow chance of spreading
        if (!pLevel.isClientSide) {
            ServerLevel world = (ServerLevel) pLevel;
            if (pRandom.nextInt(1000) == 0) { // 1 in 1000 chance per tick
                trySpread(world, pPos, pRandom);
            }
        }
    }

    private void trySpread(ServerLevel world, BlockPos pos, RandomSource random) {
        // Pick a nearby position (-1 to +1 in X, Y, Z)
        BlockPos targetPos = pos.offset(
                random.nextInt(3) - 1,
                random.nextInt(3) - 1,
                random.nextInt(3) - 1
        );

        // Don’t spread outside build height limits
        if (!world.isInWorldBounds(targetPos)) return;

        BlockState targetState = world.getBlockState(targetPos);

        // Don’t overwrite unbreakable blocks
        if (targetState.is(Blocks.BEDROCK) || targetState.is(Blocks.END_PORTAL_FRAME) || targetState.is(Blocks.END_PORTAL) || targetState.is(Blocks.NETHER_PORTAL) || targetState.is(Blocks.AIR) || targetState.is(Blocks.CAVE_AIR) || targetState.is(Blocks.VOID_AIR)) {
            return;
        }

        // Replace anything else with corruption
        world.setBlockAndUpdate(targetPos, this.defaultBlockState());
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully() && pEntity instanceof LivingEntity living && !Utils.hasEnchant(Enchantments.ALL_DAMAGE_PROTECTION, living)) {
            MobEffectInstance current = living.getEffect(MobEffects.WITHER);

            // Apply only if:
            //  - Player doesn’t already have Wither
            //  - Or the remaining duration is less than 20 ticks (1 second)
            if (current == null || current.getDuration() <= 20) {
                living.addEffect(new MobEffectInstance(
                        MobEffects.WITHER,
                        20, // 20 ticks = 1 second
                        0,  // amplifier (0 = Wither I)
                        false, // ambient
                        true   // show particles
                ));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
