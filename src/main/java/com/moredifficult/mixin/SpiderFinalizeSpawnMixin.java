package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public class SpiderFinalizeSpawnMixin {
    @Inject(method = "finalizeSpawn", at = @At(value = "RETURN"))
    private void spiderFinalizeSpawnMixin(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir){
        Mob spider = (Mob)(Object)this;
        RandomSource random = spider.getRandom();
        if (random.nextInt(100) < 17 + Math.round(MoreDifficulty.difficultNumbery) * 3) {
            Mob previosSkeleton = HelpFunctions.setJockey(
                    spider,
                    EntityType.SKELETON,
                    level,
                    difficulty,
                    spawnReason
            );
            int i = 0;
            for (int j = 0; j < 2; j++) {
                if (random.nextFloat() < 0.03F + MoreDifficulty.difficultNumbery / 100) {
                    i++;
                }
            }
            for (int j = 0; j < i; j++) {
                if (previosSkeleton != null){
                    previosSkeleton = HelpFunctions.setJockey(
                            previosSkeleton,
                            EntityType.SKELETON,
                            level,
                            difficulty,
                            spawnReason
                    );
                }
            }
        }
    }
}
