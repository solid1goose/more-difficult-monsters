package com.moredifficult.mixin;

import com.moredifficult.MoreDifficultMonsters;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public class AddNewArmorForMobs {
    @Redirect(method = "populateDefaultEquipmentSlots",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"))
    private int redirectArmorTypeInit(RandomSource instance, int bound) {
        if (!MoreDifficultMonsters.enable()) {
            return 0;
        }
        LivingEntity self = (LivingEntity)(Object)this;
        int armorType = instance.nextInt(bound);

        if (self.level().getDifficulty() == Difficulty.HARD) {
            int numberOfPasses = Math.round(ServerConfig.difficultNumbery) + ServerConfig.increaseMonsterArmor;
            for (int j = 0; j < numberOfPasses; j++) {
                if (instance.nextFloat() < 0.15F) {
                    armorType++;
                }
            }
        }
        return Math.min(armorType, 5);
    }
}