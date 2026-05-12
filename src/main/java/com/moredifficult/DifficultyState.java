package com.moredifficult;

import com.mojang.serialization.Codec;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public class DifficultyState extends SavedData {

    private float worldDifficulty = 0.5F;

    public DifficultyState(float difficulty) {
        this.worldDifficulty = difficulty;
    }

    public void setWorldDifficulty(float newDifficulty) {
        this.worldDifficulty = newDifficulty;
        setDirty();
    }

    public float getWorldDifficulty() {
        return this.worldDifficulty;
    }

    private static final Codec<DifficultyState> CODEC = Codec.FLOAT.xmap(
            DifficultyState::new,
            DifficultyState::getWorldDifficulty
    );

    private static final SavedDataType<DifficultyState> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath("more-difficult", "difficulty-state"),
            () -> new DifficultyState(0.5F),
            CODEC,
            null
    );

    public static DifficultyState getWorldDifficultyData(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(TYPE);
    }
}