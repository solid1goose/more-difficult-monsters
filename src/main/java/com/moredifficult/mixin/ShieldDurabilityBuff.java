package com.moredifficult.mixin;

import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BlocksAttacks.class)
public class ShieldDurabilityBuff {
    @ModifyVariable(
            method = "hurtBlockingItem",
            at = @At("STORE"),
            name = "itemDamage")
    private int reduceShieldDamage(int itemDamage) {
        return Math.max(1, itemDamage / 7);
    }
}
