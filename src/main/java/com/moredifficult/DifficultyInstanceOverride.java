package com.moredifficult;

import net.minecraft.world.DifficultyInstance;

public class DifficultyInstanceOverride extends DifficultyInstance {
    public DifficultyInstanceOverride(DifficultyInstance original) {
        super(
                original.getDifficulty(),
                0L,
                0L,
                0.0F
        );
    }

    @Override
    public float getSpecialMultiplier() {
        return MoreDifficulty.difficultNumbery;
    }
}
