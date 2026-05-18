package com.moredifficult.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moredifficult.ServerConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class CreeperDisableShield {
    @Unique
    private static final Float COOLDOWN = 3.5F;
    @Inject(
            method = "blockUsingItem",
            at = @At(value = "RETURN")
    )
    private void creeperDisableShield(
            ServerLevel level,
            LivingEntity attacker,
            CallbackInfo ci,
            @Local(name = "itemBlockingWith") ItemStack itemBlockingWith,
            @Local(name = "blocksAttacks") BlocksAttacks blocksAttacks) {

        if (ServerConfig.creeperDisableShieldTime != 0.0F && attacker instanceof Creeper) {
            if (blocksAttacks != null) {
                blocksAttacks.disable(level, (LivingEntity)(Object)this, ServerConfig.creeperDisableShieldTime, itemBlockingWith);
            }
        }
    }
}
