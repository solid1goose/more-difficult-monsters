package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class AttackEliteSpider {
    @Inject(
            method = "doHurtTarget",
            at = @At(value = "RETURN")
    )
    private void eliteSpiderDoHurt(
            ServerLevel level,
            Entity target,
            CallbackInfoReturnable<Boolean> cir)
    {
        Mob self = (Mob)(Object)this;
        if(HelpFunctions.isEliteMob(self) && self.getClass() == Spider.class) {
            LivingEntity livingEntity = (LivingEntity)target;
            livingEntity.addEffect(
                    new MobEffectInstance(
                            MobEffects.BLINDNESS,
                            100,
                            5
                    )
            );
        }
    }
}
