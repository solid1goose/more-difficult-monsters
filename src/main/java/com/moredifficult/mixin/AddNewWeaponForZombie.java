package com.moredifficult.mixin;

import com.moredifficult.MoreDifficultMonsters;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class AddNewWeaponForZombie {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "RETURN"))
    protected void newWeapon(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci){
        if (!MoreDifficultMonsters.enable() || ServerConfig.baseChanceForMobWeapon == 0.0) {
            return;
        }
        LivingEntity zombie = ((LivingEntity)(Object)this);
        if (random.nextFloat() < ServerConfig.baseChanceForMobWeapon + (ServerConfig.difficultNumbery * 0.05)) {
            float rand = random.nextFloat();
            if (rand < 0.01 + (ServerConfig.difficultNumbery * 0.01) + ServerConfig.increaseMonsterWeapon * 0.01) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
            } else if (rand < 0.035 + (ServerConfig.difficultNumbery * 0.01) + ServerConfig.increaseMonsterWeapon * 0.03) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            } else if (rand < 0.15 + (ServerConfig.difficultNumbery * 0.05) + ServerConfig.increaseMonsterWeapon * 0.1) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else if (rand < 0.3 + (ServerConfig.difficultNumbery * 0.1) + ServerConfig.increaseMonsterWeapon * 0.3) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            } else {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
            }
        }
    }
}
