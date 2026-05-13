package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public class ChangeChanceForArmor {
    @ModifyConstant(method = "populateDefaultEquipmentSlots",
            constant = @Constant(floatValue = 0.15F))
    private float modifyArmorChance(float original) {
        return  MoreDifficulty.enable() ? 1.0F: original;
    }
    @Redirect(method = "populateDefaultEquipmentSlots",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/DifficultyInstance;getSpecialMultiplier()F"))
    private float redirectSpecialMultiplier(DifficultyInstance difficulty) {
        return MoreDifficulty.enable() ? 0.2F + MoreDifficulty.difficultNumbery * 0.1F: difficulty.getSpecialMultiplier();
    }
}