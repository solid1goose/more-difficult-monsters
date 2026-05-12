package com.moredifficult.mixin;

import net.minecraft.world.entity.monster.skeleton.Parched;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Parched.class)
public class ParchedDebuffMixin {
    @ModifyArg(
            method = "getArrow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;I)V"
            ),
            index = 1
    )
    private int parchedDebuff(int duration){
        return 100;
    }
}
