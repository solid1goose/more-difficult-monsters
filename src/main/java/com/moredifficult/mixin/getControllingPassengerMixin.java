package com.moredifficult.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.monster.zombie.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Mob.class)
public class getControllingPassengerMixin {
    private ArrayList<Class> mobsAiEnable = new ArrayList<Class>(Arrays.asList(
            Zombie.class,
            Skeleton.class,
            Spider.class,
            CaveSpider.class
    ));
    @Inject(method = "getControllingPassenger",
            at = @At("RETURN"),
            cancellable = true)
    private void cancelControllingPassengerForSpider(CallbackInfoReturnable<LivingEntity> cir) {
        for (int i = 0; i < mobsAiEnable.size(); i++) {
            if (((Object)this).getClass() == mobsAiEnable.get(i)){
                cir.setReturnValue(null);
            }
        }
    }
}