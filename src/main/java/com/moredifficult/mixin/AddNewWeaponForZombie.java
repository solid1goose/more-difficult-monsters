package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class AddNewWeaponForZombie {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At(value = "RETURN"))
    protected void newWeapon(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci){
        if (MoreDifficulty.difficultNumbery == 0.0F) {
            return;
        }
        LivingEntity zombie = ((LivingEntity)(Object)this);
        Level world = zombie.level();
        if (random.nextFloat() < (world.getDifficulty() == Difficulty.HARD ? 0.2F + (MoreDifficulty.difficultNumbery * 0.1) : 0.00F)) {
            float rand = random.nextFloat();
            if (rand < 0.05 + (MoreDifficulty.difficultNumbery * 0.01)) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
            } else if (rand < 0.065 + (MoreDifficulty.difficultNumbery * 0.01)) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_SWORD));
            } else if (rand < 0.15 + (MoreDifficulty.difficultNumbery * 0.05)) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            } else if (rand < 0.4 + (MoreDifficulty.difficultNumbery * 0.1)) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
            } else {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
            }
        }
    }
}
