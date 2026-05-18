package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import com.moredifficult.ServerConfig;
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
        if (ServerConfig.baseChanceForMobArmor == 0.0) {
            return 0.0F;
        }
        return MoreDifficulty.enable() ? ServerConfig.baseChanceForMobArmor + ServerConfig.difficultNumbery * 0.1F: difficulty.getSpecialMultiplier();
    }
}