package com.moredifficult.mixin.hell;


import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import com.moredifficult.WitherSkeletonWeaponBuff;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonBuff {
    @Inject(
            method = "finalizeSpawn",
            at = @At(value = "RETURN")
    )
    private void witherSkeletonBuff(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        Mob self = (Mob)(Object)this;
        if(MoreDifficulty.enable() && self instanceof WitherSkeleton witherSkeleton) {
            WitherSkeletonWeaponBuff.addNewWeapon(witherSkeleton);
            RandomSource random = RandomSource.create();
            //eliteSpawn
            if (random.nextFloat() < MoreDifficulty.baseEliteChance + (MoreDifficulty.difficultNumbery * 0.005)){
                HelpFunctions.setAttributeForEliteMob(
                        self,
                        Attributes.SCALE,
                        -0.59F
                );
                HelpFunctions.setAttributeForEliteMob(
                        self,
                        Attributes.MAX_HEALTH,
                        20.0F
                );
                self.setHealth(self.getMaxHealth());
                self.addEffect(
                        new MobEffectInstance(
                                MobEffects.SPEED,
                                Integer.MAX_VALUE,
                                3
                        )
                );
                self.addEffect(
                        new MobEffectInstance(
                                MobEffects.RESISTANCE,
                                Integer.MAX_VALUE,
                                1
                        )
                );
            }
            //Buff speed
            else {
                HelpFunctions.applyNewEffectForMob(
                        self,
                        MobEffects.SPEED,
                        0.1F,
                        0.01F,
                        1,
                        false
                );
            }
        }
    }
}
