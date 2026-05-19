package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Creeper.class)
public class ExplodeCreeperMixin {
    @ModifyVariable(
            method = "explodeCreeper",
            at = @At("STORE"),
            name = "explosionMultiplier")

    private float modifyExplosionRadius(float explosionMultiplier) {
        Creeper creeper = (Creeper)(Object)this;
        if (HelpFunctions.isEliteMob(creeper))
        {
            return explosionMultiplier * 1.5F;
        }
        return explosionMultiplier;
    }
}
