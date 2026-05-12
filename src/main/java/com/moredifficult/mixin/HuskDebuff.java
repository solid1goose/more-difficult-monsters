package com.moredifficult.mixin;

import net.minecraft.world.entity.monster.zombie.Husk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Husk.class)
public class HuskDebuff {
    @ModifyConstant(
            method = "finalizeSpawn",
            constant = @Constant(floatValue = 0.1F)
    )
    private float huskDebuff(float original) {
        return 0.01F;
    }
}
