package me.goldenkitten.fallen_age.event;

import com.mojang.datafixers.util.Pair;
import me.goldenkitten.fallen_age.FallenAge;
import me.goldenkitten.fallen_age.Utils;
import me.goldenkitten.fallen_age.world_gen.biome.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = FallenAge.MOD_ID)
public class PlayerSpawnHandler {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ServerLevel level = player.serverLevel();

        // Only run if the player has no spawn point yet (first login)
        BlockPos spawnSearchCenter = level.getSharedSpawnPos(); // or (0, 64, 0)
        Predicate<Holder<Biome>> predicate = biomeHolder ->
                biomeHolder.is(ModBiomes.HOPE_BIOME); // Target biome

        Pair<BlockPos, Holder<Biome>> result =
                level.getChunkSource().getGenerator().getBiomeSource().findClosestBiome3d(
                        spawnSearchCenter,
                        6400, // search radius
                        32,   // horizontal step
                        64,   // vertical step
                        predicate,
                        level.getChunkSource().randomState().sampler(),
                        level
                );

        if (result != null) {
            BlockPos biomePos = result.getFirst();
            // find a safe Y at biomePos (surface or bedrock check)
            BlockPos safePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, biomePos);

            BlockPos tpPos = Utils.getSafeHighestBlockAt(level, safePos);
            player.setRespawnPosition(level.dimension(), tpPos, player.getYRot(), true, false);
            player.teleportTo(level, tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5, player.getYRot(), player.getXRot());
        }
    }
}
