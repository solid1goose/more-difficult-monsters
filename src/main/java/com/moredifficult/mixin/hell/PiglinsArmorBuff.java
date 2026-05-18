package com.moredifficult.mixin.hell;

import com.moredifficult.MoreDifficulty;
import com.moredifficult.ServerConfig;
import net.minecraft.world.entity.monster.piglin.Piglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Piglin.class)
public class PiglinsArmorBuff {
   @ModifyConstant(
           method = "maybeWearArmor",
           constant = @Constant(floatValue = 0.1F)
   )
    private float piglinsArmorBuff(float constant){
       if (MoreDifficulty.enable()){
           return 0.6F + (ServerConfig.difficultNumbery * 0.1F);
       }
       return constant;
   }
}
