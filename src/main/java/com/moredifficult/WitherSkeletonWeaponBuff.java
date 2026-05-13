package com.moredifficult;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WitherSkeletonWeaponBuff {
    public static void addNewWeapon(WitherSkeleton witherSkeleton){
        RandomSource random = RandomSource.create();
        if (random.nextFloat() < 0.2 + MoreDifficulty.difficultNumbery * 0.01){
            ItemStack newWeapon = new ItemStack(Items.IRON_SWORD);
            witherSkeleton.setItemSlot(EquipmentSlot.MAINHAND, newWeapon);
        }}
}
