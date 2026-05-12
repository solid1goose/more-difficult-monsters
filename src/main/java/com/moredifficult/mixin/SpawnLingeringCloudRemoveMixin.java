package com.moredifficult.mixin;

import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Creeper.class)
public class SpawnLingeringCloudRemoveMixin {
    @Inject(method = "spawnLingeringCloud", at = @At(value = "HEAD"), cancellable = true)
    private void SpawnLingeringCloudRemove(CallbackInfo ci){
        ci.cancel();
    }
}
