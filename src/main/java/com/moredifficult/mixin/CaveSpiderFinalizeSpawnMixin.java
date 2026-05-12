package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CaveSpider.class)
public class CaveSpiderFinalizeSpawnMixin {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void caveSpiderFinalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        LivingEntity spider = ((LivingEntity)(Object)this);
        RandomSource random = spider.getRandom();
        if (random.nextInt(100) < 17 + Math.round(MoreDifficulty.difficultNumbery) * 3) {
            Skeleton startSkeleton = EntityType.SKELETON.create(spider.level(), EntitySpawnReason.JOCKEY);
            if (startSkeleton != null) {
                startSkeleton.snapTo(spider.getX(), spider.getY(), spider.getZ(), spider.getYRot(), 0.0F);
                startSkeleton.finalizeSpawn(level, difficulty, spawnReason, null);
                startSkeleton.startRiding(spider, false, false);
            }
        }
    }
}
