package com.moredifficult.mixin;

import com.moredifficult.MoreDifficulty;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CaveSpider.class)
public class CaveSpiderDebuffMixin {
    @ModifyVariable(
        method = "doHurtTarget",
            at = @At("STORE"),
            name = "poisonTime",
            ordinal = 0
    )
    private int CaveSpiderDebuff(int poisonTime) {
        Mob spider = ((Mob)(Object)this);
        if (spider.level().getDifficulty() == Difficulty.NORMAL) {
            poisonTime = 7;
        } else if (spider.level().getDifficulty() == Difficulty.HARD && MoreDifficulty.difficultNumbery != 0.0F) {
            poisonTime = 5 + Math.round(Math.min(3.0F, MoreDifficulty.difficultNumbery));
        }
        return poisonTime;
    }
}
