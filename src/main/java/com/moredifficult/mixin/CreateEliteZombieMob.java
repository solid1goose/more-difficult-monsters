package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class CreateEliteZombieMob {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void createBossMob(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData, CallbackInfoReturnable<SpawnGroupData> cir){
        Mob mob = (Mob)(Object)this;
        if((mob instanceof Zombie || mob instanceof Husk)) {
            RandomSource random = RandomSource.create();
            float diff = ServerConfig.difficultNumbery;
            //BOSS
            if (ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance * 0.5 + (diff * 0.005)) {
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.SPEED,
                        1.0F,
                        1.0F,
                        2,
                        false);
                if (ServerConfig.difficultNumbery > 8) {
                    HelpFunctions.applyNewEffectForMob(
                            mob,
                            MobEffects.STRENGTH,
                            1.0F,
                            1.0F,
                            1,
                            false);
                }
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.RESISTANCE,
                        1.0F,
                        1.0F,
                        1,
                        false);
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.JUMP_BOOST,
                        1.0F,
                        1.0F,
                        1,
                        false);
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.INFESTED,
                        1.0F,
                        1.0F,
                        1,
                        false);
                HelpFunctions.setAttributeForEliteMob(mob, Attributes.SCALE,0.35F);
                HelpFunctions.setAttributeForEliteMob(mob, Attributes.MAX_HEALTH,40.0F);
            }
            //MINI BOSS
            else if (ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance + (diff * 0.01)) {
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.SPEED,
                        1.0F,
                        1.0F,
                        1,
                        false);
                if (ServerConfig.difficultNumbery > 10) {
                    HelpFunctions.applyNewEffectForMob(
                            mob,
                            MobEffects.STRENGTH,
                            1.0F,
                            1.0F,
                            1,
                            false);
                }
                HelpFunctions.applyNewEffectForMob(
                        mob,
                        MobEffects.JUMP_BOOST,
                        1.0F,
                        1.0F,
                        1,
                        false);
                HelpFunctions.setAttributeForEliteMob(mob, Attributes.SCALE,0.1F);
                HelpFunctions.setAttributeForEliteMob(mob, Attributes.MAX_HEALTH,20.0F);
            }
        }
        mob.setHealth(mob.getMaxHealth());
    }
}
