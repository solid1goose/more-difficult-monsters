package com.moredifficult;

import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.function.Predicate;

public class SpawnModification {
    public static void modificationMobSpawn() {
        BiomeModifications.create(Identifier.fromNamespaceAndPath("more-difficult-monsters", "modify-skeleton-spawn"))
                .add(
                        ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.foundInOverworld(),
                        ctx -> {
                            ctx.getMobSpawnSettings().removeSpawnsOfEntityType(EntityType.SKELETON);
                            ctx.getMobSpawnSettings().addSpawn(MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 1, 2), 50);
                        }
                )
                .add(
                        ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.foundInOverworld(),
                        ctx -> {
                            ctx.getMobSpawnSettings().removeSpawnsOfEntityType(EntityType.STRAY);
                            ctx.getMobSpawnSettings().addSpawn(MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(EntityType.STRAY, 1, 2), 20);
                        }
                );
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
                EntityType.CAVE_SPIDER,
                65,
                1,
                2);
    }
}
