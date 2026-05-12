package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobCategory.class)
public class IncreaseMonsters {
    @Inject(
            method = "getMaxInstancesPerChunk",
            at = @At("RETURN"),
            cancellable = true
    )
    private void increaseMonsterCap(CallbackInfoReturnable<Integer> cir) {
        MobCategory self = (MobCategory)(Object)this;
        if (self == MobCategory.MONSTER) {
            cir.setReturnValue(70 + Math.max(Math.round(MoreDifficulty.difficultNumbery * 5), 100));
        }
    }
}
