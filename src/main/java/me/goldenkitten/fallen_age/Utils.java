package me.goldenkitten.fallen_age;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static boolean hasEnchant(Enchantment enchant, LivingEntity pEntity) {
        return EnchantmentHelper.getEnchantmentLevel(enchant, pEntity) > 0;
    }

    public static List<ResourceKey<Biome>> getEndBiomes() {
        return Arrays.asList(
                Biomes.THE_END,
                Biomes.END_HIGHLANDS,
                Biomes.END_MIDLANDS,
                Biomes.SMALL_END_ISLANDS,
                Biomes.END_BARRENS
        );
    }

    public static List<ResourceKey<Biome>> getNetherBiomes() {
        return Arrays.asList(
                Biomes.NETHER_WASTES,
                Biomes.WARPED_FOREST,
                Biomes.CRIMSON_FOREST,
                Biomes.SOUL_SAND_VALLEY,
                Biomes.BASALT_DELTAS
        );
    }

    public static List<ResourceKey<Biome>> getOverworldBiomesNoCaves() {
        return Arrays.asList(
                Biomes.PLAINS,
                Biomes.SUNFLOWER_PLAINS,
                Biomes.SNOWY_PLAINS,
                Biomes.ICE_SPIKES,
                Biomes.DESERT,
                Biomes.SWAMP,
                Biomes.MANGROVE_SWAMP,
                Biomes.FOREST,
                Biomes.FLOWER_FOREST,
                Biomes.BIRCH_FOREST,
                Biomes.DARK_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.TAIGA,
                Biomes.SNOWY_TAIGA,
                Biomes.SAVANNA,
                Biomes.SAVANNA_PLATEAU,
                Biomes.WINDSWEPT_HILLS,
                Biomes.WINDSWEPT_GRAVELLY_HILLS,
                Biomes.WINDSWEPT_FOREST,
                Biomes.WINDSWEPT_SAVANNA,
                Biomes.JUNGLE,
                Biomes.SPARSE_JUNGLE,
                Biomes.BAMBOO_JUNGLE,
                Biomes.BADLANDS,
                Biomes.ERODED_BADLANDS,
                Biomes.WOODED_BADLANDS,
                Biomes.MEADOW,
                Biomes.CHERRY_GROVE,
                Biomes.GROVE,
                Biomes.SNOWY_SLOPES,
                Biomes.FROZEN_PEAKS,
                Biomes.JAGGED_PEAKS,
                Biomes.STONY_PEAKS,
                Biomes.RIVER,
                Biomes.FROZEN_RIVER,
                Biomes.BEACH,
                Biomes.SNOWY_BEACH,
                Biomes.STONY_SHORE,
                Biomes.WARM_OCEAN,
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN,
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN,
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN,
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN,
                Biomes.MUSHROOM_FIELDS
        );
    }

    public static List<ResourceKey<Biome>> getOverworldBiomes() {
        return Arrays.asList(
                Biomes.PLAINS,
                Biomes.SUNFLOWER_PLAINS,
                Biomes.SNOWY_PLAINS,
                Biomes.ICE_SPIKES,
                Biomes.DESERT,
                Biomes.SWAMP,
                Biomes.MANGROVE_SWAMP,
                Biomes.FOREST,
                Biomes.FLOWER_FOREST,
                Biomes.BIRCH_FOREST,
                Biomes.DARK_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.TAIGA,
                Biomes.SNOWY_TAIGA,
                Biomes.SAVANNA,
                Biomes.SAVANNA_PLATEAU,
                Biomes.WINDSWEPT_HILLS,
                Biomes.WINDSWEPT_GRAVELLY_HILLS,
                Biomes.WINDSWEPT_FOREST,
                Biomes.WINDSWEPT_SAVANNA,
                Biomes.JUNGLE,
                Biomes.SPARSE_JUNGLE,
                Biomes.BAMBOO_JUNGLE,
                Biomes.BADLANDS,
                Biomes.ERODED_BADLANDS,
                Biomes.WOODED_BADLANDS,
                Biomes.MEADOW,
                Biomes.CHERRY_GROVE,
                Biomes.GROVE,
                Biomes.SNOWY_SLOPES,
                Biomes.FROZEN_PEAKS,
                Biomes.JAGGED_PEAKS,
                Biomes.STONY_PEAKS,
                Biomes.RIVER,
                Biomes.FROZEN_RIVER,
                Biomes.BEACH,
                Biomes.SNOWY_BEACH,
                Biomes.STONY_SHORE,
                Biomes.WARM_OCEAN,
                Biomes.LUKEWARM_OCEAN,
                Biomes.DEEP_LUKEWARM_OCEAN,
                Biomes.OCEAN,
                Biomes.DEEP_OCEAN,
                Biomes.COLD_OCEAN,
                Biomes.DEEP_COLD_OCEAN,
                Biomes.FROZEN_OCEAN,
                Biomes.DEEP_FROZEN_OCEAN,
                Biomes.MUSHROOM_FIELDS,
                Biomes.DRIPSTONE_CAVES,
                Biomes.LUSH_CAVES,
                Biomes.DEEP_DARK
        );
    }

    public static List<ResourceKey<Biome>> getAllBiomes() {
        List<ResourceKey<Biome>> result = new ArrayList<>();

        for (Field field : Biomes.class.getDeclaredFields()) {
            // only accept public static final fields of type ResourceKey<Biome>
            if (Modifier.isStatic(field.getModifiers()) &&
                    Modifier.isPublic(field.getModifiers()) &&
                    Modifier.isFinal(field.getModifiers()) &&
                    ResourceKey.class.isAssignableFrom(field.getType())) {

                try {
                    Object value = field.get(null); // static field => null target
                    if (value instanceof ResourceKey<?> key) {
                        if (key.isFor(Registries.BIOME)) {
                            @SuppressWarnings("unchecked")
                            ResourceKey<Biome> biomeKey = (ResourceKey<Biome>) key;
                            if (biomeKey != Biomes.THE_VOID) {
                                result.add(biomeKey);
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to access field " + field.getName(), e);
                }
            }
        }
        return result;
    }
}
