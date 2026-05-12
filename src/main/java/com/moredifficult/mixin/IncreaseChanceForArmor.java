package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Mob.class)
public class IncreaseChanceForArmor {
    @ModifyConstant(
            method = "populateDefaultEquipmentSlots",
            constant = @Constant(floatValue = 0.15F)
    )
    private float increaseArmorChance(float original) {
        if (MoreDifficulty.difficultNumbery == 0.0F) {
            return 0.15F;
        }
        return 0.3F * MoreDifficulty.difficultNumbery;
    }
}
