package com.moredifficult;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.function.Predicate;

public class SpawnModification {
    private static final Predicate<BiomeSelectionContext> defaultBiomes = BiomeSelectors.includeByKey(
                    // Равнины и снег
                    Biomes.PLAINS,
                    Biomes.SUNFLOWER_PLAINS,
                    Biomes.SNOWY_PLAINS,
                    Biomes.ICE_SPIKES,
                    // Пустыня и бедленды
                    Biomes.DESERT,
                    Biomes.BADLANDS,
                    Biomes.ERODED_BADLANDS,
                    Biomes.WOODED_BADLANDS,
                    // Леса
                    Biomes.FOREST,
                    Biomes.FLOWER_FOREST,
                    Biomes.BIRCH_FOREST,
                    Biomes.OLD_GROWTH_BIRCH_FOREST,
                    Biomes.DARK_FOREST,
                    Biomes.PALE_GARDEN,
                    // Тайга
                    Biomes.TAIGA,
                    Biomes.SNOWY_TAIGA,
                    Biomes.OLD_GROWTH_PINE_TAIGA,
                    Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                    // Джунгли
                    Biomes.JUNGLE,
                    Biomes.SPARSE_JUNGLE,
                    Biomes.BAMBOO_JUNGLE,
                    // Саванна
                    Biomes.SAVANNA,
                    Biomes.SAVANNA_PLATEAU,
                    Biomes.WINDSWEPT_SAVANNA,
                    // Горы и холмы
                    Biomes.WINDSWEPT_HILLS,
                    Biomes.WINDSWEPT_GRAVELLY_HILLS,
                    Biomes.WINDSWEPT_FOREST,
                    Biomes.MEADOW,
                    Biomes.CHERRY_GROVE,
                    Biomes.GROVE,
                    Biomes.SNOWY_SLOPES,
                    Biomes.FROZEN_PEAKS,
                    Biomes.JAGGED_PEAKS,
                    Biomes.STONY_PEAKS,
                    // Болота
                    Biomes.SWAMP,
                    Biomes.MANGROVE_SWAMP,
                    // Реки и пляжи
                    Biomes.RIVER,
                    Biomes.FROZEN_RIVER,
                    Biomes.BEACH,
                    Biomes.SNOWY_BEACH,
                    Biomes.STONY_SHORE,
                    // Океаны
                    Biomes.WARM_OCEAN,
                    Biomes.LUKEWARM_OCEAN,
                    Biomes.DEEP_LUKEWARM_OCEAN,
                    Biomes.OCEAN,
                    Biomes.DEEP_OCEAN,
                    Biomes.COLD_OCEAN,
                    Biomes.DEEP_COLD_OCEAN,
                    Biomes.FROZEN_OCEAN,
                    Biomes.DEEP_FROZEN_OCEAN,
                    // Прочее
                    Biomes.MUSHROOM_FIELDS,
                    Biomes.DRIPSTONE_CAVES,
                    Biomes.LUSH_CAVES);

    public static void reduceSpawn(){
        BiomeModifications.create(Identifier.fromNamespaceAndPath("more-difficult", "reduce-skeleton-spawn"))
                .add(
                        ModificationPhase.REMOVALS,
                        BiomeSelectors.foundInOverworld(),
                        ctx -> {
                            ctx.getMobSpawnSettings().removeSpawnsOfEntityType(EntityType.SKELETON);
                        }
                );
    }

    public static void addNewSpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER,
                EntityType.HUSK,
                50,
                1,
                4);
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER,
                EntityType.PARCHED,
                20,
                1,
                2);
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER,
                EntityType.STRAY,
                20,
                1,
                2);
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER,
                EntityType.SKELETON,
                30,
                1,
                2);
        BiomeModifications.addSpawn(BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                MobCategory.MONSTER,
                EntityType.CAVE_SPIDER,
                40,
                1,
                2);
    }
}
