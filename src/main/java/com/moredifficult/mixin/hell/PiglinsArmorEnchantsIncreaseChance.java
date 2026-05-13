package com.moredifficult.mixin.hell;

import com.moredifficult.MoreDifficulty;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.piglin.Piglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Mob.class)
public class PiglinsArmorEnchantsIncreaseChance {
    @ModifyArg(
            method = "enchantSpawnedArmor",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Mob;enchantSpawnedEquipment(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/util/RandomSource;FLnet/minecraft/world/DifficultyInstance;)V"),
            index = 3
    )
    private float increaseChance(float constant){
        if (MoreDifficulty.enable() && (Mob)(Object)this instanceof Piglin) {
            return constant + (MoreDifficulty.difficultNumbery * 0.1F);
        }
        return constant;
    }
}
