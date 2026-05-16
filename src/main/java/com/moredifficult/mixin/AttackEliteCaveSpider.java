package com.moredifficult.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moredifficult.HelpFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CaveSpider.class)
public class AttackEliteCaveSpider {
    @Inject(
            method = "doHurtTarget",
            at = @At("RETURN")
    )
    private void attackBossCaveSpider(
            ServerLevel level,
            Entity target,
            CallbackInfoReturnable<Boolean> cir)
    {
        CaveSpider spider = (CaveSpider)(Object)this;
        if (HelpFunctions.isEliteMob(spider) && cir.getReturnValue()) {
            LivingEntity livingTarget = (LivingEntity)target;
            livingTarget.addEffect(
                    new MobEffectInstance(
                            MobEffects.WITHER,
                            50,
                            1
                    )
            );
        }
    }
}
