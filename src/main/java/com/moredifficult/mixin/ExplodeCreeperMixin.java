package com.moredifficult.mixin;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
        AttributeInstance attr = creeper.getAttribute(Attributes.SCALE);
        if (
        attr != null &&
        attr.hasModifier(Identifier.fromNamespaceAndPath("more-difficult", "elite")))
        {
            return explosionMultiplier * 1.5F;
        }
        return explosionMultiplier;
    }
}
