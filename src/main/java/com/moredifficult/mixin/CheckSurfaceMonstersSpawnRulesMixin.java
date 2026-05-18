package com.moredifficult.mixin;

import com.moredifficult.ServerConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Monster.class)
public class CheckSurfaceMonstersSpawnRulesMixin {
    @Inject(
            method = "checkSurfaceMonstersSpawnRules",
            at = @At("HEAD"),
            cancellable = true
    )
    private static <T extends Mob> void checkSurfaceMonstersSpawnRules(
            EntityType<T> type, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos,
            RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        if (ServerConfig.addNewMobsInSpawnPool && type == EntityType.HUSK || type == EntityType.STRAY || type == EntityType.PARCHED) {
            cir.setReturnValue(Monster.checkMonsterSpawnRules(type, level, spawnReason, pos, random));
        }
    }
}