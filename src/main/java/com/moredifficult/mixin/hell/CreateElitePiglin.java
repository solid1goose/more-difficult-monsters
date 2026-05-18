package com.moredifficult.mixin.hell;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficulty;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Piglin.class)
public class CreateElitePiglin {
    @Inject(
            method = "finalizeSpawn",
            at = @At("RETURN")
    )
    private void createElitePiglin(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData groupData,
            CallbackInfoReturnable<SpawnGroupData> cir)
    {
        RandomSource random = RandomSource.create();
        if (spawnReason == EntitySpawnReason.JOCKEY) return;
        if (ServerConfig.enableElite && random.nextFloat() < ServerConfig.baseEliteChance + (ServerConfig.difficultNumbery * 0.01)) {
            Piglin self = (Piglin)(Object)this;
            Piglin jokey = EntityType.PIGLIN.create(self.level(), EntitySpawnReason.JOCKEY);
            if (jokey != null){
                HelpFunctions.setJockey(
                        self,
                        jokey,
                        level,
                        difficulty
                );
                HelpFunctions.setAttributeForEliteMob(
                        self,
                        Attributes.SCALE,
                        0.2F
                );
                ItemStack itemStack = new ItemStack(Items.GOLDEN_AXE);
                self.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
                jokey.setBaby(false);
                self.setBaby(false);
                ItemStack crossbow = new ItemStack(Items.CROSSBOW);
                HelpFunctions.enchantItem(
                        crossbow,
                        Enchantments.FLAME,
                        self.level(),
                        1
                );
                jokey.setItemSlot(EquipmentSlot.MAINHAND, crossbow);
            }
        }
    }
}
