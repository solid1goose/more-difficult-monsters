package com.moredifficult;

import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.Nullable;

public class HelpFunctions {
    public static boolean applyNewEffectForMob(Mob mob, Holder<MobEffect> effect, float chance, float difficultModification, int repetitions, boolean hideParticles) {
        RandomSource random = mob.getRandom();
        int effectPower = 0;
        for(int i = 0; i < repetitions; i++){
            if (random.nextFloat() < chance + (MoreDifficulty.difficultNumbery * difficultModification)) {
                effectPower++;
            }
        }
        if (effectPower > 0) {
            mob.addEffect(new MobEffectInstance(
                    effect,
                    Integer.MAX_VALUE,
                    effectPower,
                    hideParticles,
                    true
            ));
            return true;
        }
        return false;
    }

    public static boolean setAttributeForEliteMob(Mob mob, Holder<Attribute> attribute, float scale){
        AttributeInstance scaleAttr = mob.getAttribute(attribute);
        if (scaleAttr != null) {
            scaleAttr.addPermanentModifier(
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath("more-difficult", "elite"),
                            scale,
                            AttributeModifier.Operation.ADD_VALUE
                    )
            );
            return true;
        }
        return false;
    }

    @Nullable
    public static <T extends Mob> Mob setJockey(
            Mob vehicle,
            EntityType<T> jokey,
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason)
    {
        T jokeyInitialization = jokey.create(vehicle.level(), EntitySpawnReason.JOCKEY);
        if (jokeyInitialization != null) {
            jokeyInitialization.snapTo(vehicle.getX(), vehicle.getY(), vehicle.getZ(), vehicle.getYRot(), 0.0F);
            jokeyInitialization.finalizeSpawn(level, difficulty, spawnReason, null);
            jokeyInitialization.startRiding(vehicle, false, false);
        }
        return jokeyInitialization;
    }
}
