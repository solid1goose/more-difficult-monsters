package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.skeleton.Parched;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.world.entity.ai.attributes.Attributes;

@Mixin(Mob.class)
public class CreateEliteSkeletonMob {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void createBossSkeleton(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        Mob self = (Mob)(Object)this;
        RandomSource random = RandomSource.create();
        if (
                ServerConfig.enableElite &&
                (self instanceof Skeleton ||
                self instanceof Stray ||
                self instanceof Parched) &&
                !self.isPassenger() &&
                random.nextFloat() < ServerConfig.baseEliteChance + (ServerConfig.difficultNumbery * 0.001F)
        ) {
            self.addEffect(
                    new MobEffectInstance(
                            MobEffects.SPEED,
                            Integer.MAX_VALUE,
                            1,
                            false,
                            true
                    )
            );
            HelpFunctions.setAttributeForEliteMob(self, Attributes.SCALE, 0.30F);
            HelpFunctions.setAttributeForEliteMob(self, Attributes.MAX_HEALTH, 10.0F);
            self.setHealth(self.getMaxHealth());
            HelpFunctions.setJockey(
                    self,
                    EntityType.SKELETON,
                    level,
                    difficulty,
                    spawnReason
            );
        }
    }
}
