package com.moredifficult.mixin;

import com.moredifficult.MoreDifficultMonsters;
import com.moredifficult.ServerConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Arrays;

@Mixin(LivingEntity.class)
public class CreeperExplosionRework {

    @ModifyArg(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V"
            ),
            index = 2
    )
    private float creeperExplosionRework(
            ServerLevel level,
            DamageSource source,
            float damage
    ) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (
                ServerConfig.creeperDoesLessDamageToMonsters &&
                Arrays.stream(MoreDifficultMonsters.monsters).anyMatch(type -> self.getType() == type)
                && source.is(DamageTypeTags.IS_EXPLOSION)
                && source.getEntity() instanceof Creeper) {
            return damage / 3.0F;
        }
        return damage;
    }
}
