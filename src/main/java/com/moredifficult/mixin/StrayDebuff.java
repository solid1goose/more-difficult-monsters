package com.moredifficult.mixin;

import com.moredifficult.ServerConfig;
import net.minecraft.world.entity.monster.skeleton.Stray;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Stray.class)
public class StrayDebuff {
    @ModifyArg(
            method = "getArrow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;I)V"
            ),
            index = 1
    )
    private int strayDebuff(int duration) {
        return ServerConfig.addNewMobsInSpawnPool? 100: duration;
    }
}
