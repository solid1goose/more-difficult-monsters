package com.moredifficult.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.skeleton.Stray;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Stray.class)
public class CheckStraySpawnRulesMixin {
    @Inject(
            method = "checkStraySpawnRules",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void checkStraySpawnRules(EntityType<Stray> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
