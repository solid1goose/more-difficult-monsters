package com.moredifficult;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jspecify.annotations.Nullable;

public class HelpFunctions {
    public static boolean applyNewEffectForMob(Mob mob, Holder<MobEffect> effect, float chance, float difficultModification, int repetitions, boolean hideParticles) {
        RandomSource random = mob.getRandom();
        int effectPower = 0;
        for (int i = 0; i < repetitions; i++) {
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

    public static boolean setAttributeForEliteMob(Mob mob, Holder<Attribute> attribute, float modificator) {
        AttributeInstance attr = mob.getAttribute(attribute);
        if (attr != null) {
            attr.addPermanentModifier(
                    new AttributeModifier(
                            Identifier.fromNamespaceAndPath("more-difficult", "elite"),
                            modificator,
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
            Mob jokey,
            ServerLevelAccessor level,
            DifficultyInstance difficulty
    ) {
        if (jokey != null) {
            jokey.snapTo(vehicle.getX(), vehicle.getY(), vehicle.getZ(), vehicle.getYRot(), 0.0F);
            jokey.finalizeSpawn(level, difficulty, EntitySpawnReason.JOCKEY, null);
            jokey.startRiding(vehicle, false, false);
        }
        return jokey;
    }

    @Nullable
    public static <T extends Mob> Mob setJockey(
            Mob vehicle,
            EntityType<T> jokey,
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason) {
        T jokeyInitialization = jokey.create(vehicle.level(), EntitySpawnReason.JOCKEY);
        if (jokeyInitialization != null) {
            jokeyInitialization.snapTo(vehicle.getX(), vehicle.getY(), vehicle.getZ(), vehicle.getYRot(), 0.0F);
            jokeyInitialization.finalizeSpawn(level, difficulty, spawnReason, null);
            jokeyInitialization.startRiding(vehicle, false, false);
        }
        return jokeyInitialization;
    }

    public static void enchantItem(ItemStack item, ResourceKey<Enchantment> enchantment, Level level, int enchantmentLevel) {
        Holder<Enchantment> enchantmentHolder = level
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(enchantment);
        item.enchant(enchantmentHolder, enchantmentLevel);
    }

    public static boolean isEliteMob(Mob mob) {
        AttributeInstance attr = mob.getAttribute(Attributes.SCALE);
        return attr != null && attr.hasModifier(Identifier.fromNamespaceAndPath("more-difficult", "elite"));
    }
}
