package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class CreeperSpawnMixin {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void onSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        if((Object)this instanceof Creeper creeper && MoreDifficulty.difficultNumbery != 0.0F){
            RandomSource random = RandomSource.create();
            boolean hideInvisibleParticles = random.nextFloat() < (0.15 * (MoreDifficulty.difficultNumbery * 0.02));
            boolean appliedInvisibleEffect = HelpFunctions.applyNewEffectForMob(
                    creeper,
                    MobEffects.INVISIBILITY,
                    0.15F,
                    0.005F,
                    1,
                    hideInvisibleParticles);

            HelpFunctions.applyNewEffectForMob(
                    creeper,
                    MobEffects.SPEED,
                    0.2F,
                    0.01F,
                    2 + Math.round(MoreDifficulty.difficultNumbery / 30.0F),
                    appliedInvisibleEffect);

        }
    }
}