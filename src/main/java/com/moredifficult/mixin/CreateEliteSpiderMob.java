package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Spider.class)
public class CreateEliteSpiderMob {
    @Inject(
            method = "finalizeSpawn",
            at = @At("HEAD"),
            cancellable = true
    )
    private void createEliteSpiderMob(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        RandomSource random = RandomSource.create();
        if (ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance + ServerConfig.difficultNumbery * 0.01){
            Spider self = (Spider)(Object)this;
            HelpFunctions.setAttributeForEliteMob(
                    self,
                    Attributes.ATTACK_DAMAGE,
                    3.0F
            );
            HelpFunctions.setAttributeForEliteMob(
                    self,
                    Attributes.SCALE,
                    0.3F
            );
            HelpFunctions.setAttributeForEliteMob(
                    self,
                    Attributes.MAX_HEALTH,
                    20F
            );
            self.setHealth(self.getMaxHealth());
            self.addEffect(
                    new MobEffectInstance(
                            MobEffects.SPEED,
                            Integer.MAX_VALUE,
                            2
                    )
            );
            self.addEffect(
                    new MobEffectInstance(
                            MobEffects.INVISIBILITY,
                            Integer.MAX_VALUE,
                            1
                    )
            );
            cir.cancel();
        }
    }
}
