package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CaveSpider.class)
public class CreateEliteCaveSpiderMob {
    @Inject(
            method = "finalizeSpawn",
            at = @At(value = "RETURN")
    )
    private void createBossSpiderMob(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        RandomSource random = RandomSource.create();
        if(ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance + ServerConfig.difficultNumbery * 0.01){
            CaveSpider self = (CaveSpider)(Object)this;
            HelpFunctions.setAttributeForEliteMob(
                    self,
                    Attributes.SCALE,
                    0.6F
            );
            HelpFunctions.setAttributeForEliteMob(
                    self,
                    Attributes.MAX_HEALTH,
                    15.0F
            );
            self.setHealth(self.getMaxHealth());
            self.addEffect(
                    new MobEffectInstance(
                            MobEffects.SPEED,
                            Integer.MAX_VALUE,
                            2 + Math.min(Math.round(ServerConfig.difficultNumbery / 10), 2)
                    )
            );
        }
    }
}
