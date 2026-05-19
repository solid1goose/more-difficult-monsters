package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class CreateEliteCreeperMob {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void createBossCreeperMob(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        Mob self = (Mob)(Object)this;
        RandomSource random = RandomSource.create();
        if (self instanceof Creeper) {
            if (ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance + (ServerConfig.difficultNumbery * 0.01)) {
                HelpFunctions.setAttributeForEliteMob(self, Attributes.SCALE, 0.4F);
                HelpFunctions.setAttributeForEliteMob(self, Attributes.MAX_HEALTH, 20.0F);
                self.setHealth(self.getMaxHealth());
            }
        }
    }
}
