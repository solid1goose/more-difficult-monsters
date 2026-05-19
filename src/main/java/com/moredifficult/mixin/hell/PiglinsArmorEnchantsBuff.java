package com.moredifficult.mixin.hell;

import com.moredifficult.HelpFunctions;
import com.moredifficult.MoreDifficultMonsters;
import com.moredifficult.ServerConfig;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Piglin.class)
public class PiglinsArmorEnchantsBuff {
    @Inject(method = "maybeWearArmor",
            at = @At(value = "RETURN")
    )
    private void armorEnchantsBuff(EquipmentSlot slot, ItemStack itemStack, RandomSource random, CallbackInfo ci) {
        if (MoreDifficultMonsters.enable()){
            return;
        }
        Piglin self =(Piglin)(Object)this;
        ItemStack item = self.getItemBySlot(slot);
        int protectionLevel = 0;
        int thornsLevel = 0;
        RandomSource randomSource = RandomSource.create();
        for (int i = 0; i < 4; i++){
            if (randomSource.nextFloat() < 0.2F + (ServerConfig.difficultNumbery * 0.1F) ) {
                protectionLevel++;
            }
        }
        if (randomSource.nextFloat() < 0.1F + ServerConfig.difficultNumbery * 0.005F) {
            thornsLevel++;
        }
        if(item != null && protectionLevel > 0) {
            HelpFunctions.enchantItem(item, Enchantments.PROTECTION, self.level(), protectionLevel);
        }
        if(item != null && thornsLevel > 0) {
            HelpFunctions.enchantItem(item, Enchantments.THORNS, self.level(), thornsLevel);
        }
    }
}
