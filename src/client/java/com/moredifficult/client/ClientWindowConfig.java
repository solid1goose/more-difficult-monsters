package com.moredifficult.client;

import com.moredifficult.ServerConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClientWindowConfig {
    public static net.minecraft.client.gui.screens.Screen createConfigScreen(Screen parent) {
        ServerConfig.load();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("More Difficulty Config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Difficulty"),
                        ServerConfig.difficultNumbery)
                .setDefaultValue(1.0F)
                .setMin(0.0F)
                .setMax(100.0F)
                .setTooltip(Component.literal("The most basic parameter affects absolutely everything, armor, weapons, the number of mobs and their elite versions, setting this value to 0.0 will stop producing improved weapons and armor, but all the remaining changes will work."))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.DIFFICULTY, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Chance to spawn a mob in armor"),
                        ServerConfig.baseChanceForMobArmor)
                .setDefaultValue(0.15F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(Component.literal("It affects the base chance of spawning an enemy with armor. This chance is also affected by \"Difficulty\", BUT setting the value to 1.0 will always be 100 percent, and 0.0 will always be 0%, regardless of difficulty."))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.BASE_ARMOR_CHANCE, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startIntField(
                        Component.literal("Increase the chance of better monster armor"),
                        ServerConfig.increaseMonsterArmor)
                .setDefaultValue(0)
                .setMin(0)
                .setMax(30)
                .setTooltip(Component.literal("Enhances the armor quality for monsters"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.INCREASE_MONSTER_ARMOR, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Chance to spawn a mob with weapon"),
                        ServerConfig.baseChanceForMobWeapon)
                .setDefaultValue(0.1F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(Component.literal("It affects the base chance of spawning an enemy with weapon. This chance is also affected by \"Difficulty\", BUT setting the value to 1.0 will always be 100 percent, and 0.0 will always be 0%, regardless of difficulty."))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.BASE_WEAPON_CHANCE, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Increase the chance of better monster weapon"),
                        ServerConfig.increaseMonsterWeapon)
                .setDefaultValue(0.0F)
                .setMin(0.0F)
                .setMax(100.0F)
                .setTooltip(Component.literal("Enhances the weapon quality for monsters, set 100.0 always diamond axe"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.INCREASE_MONSTER_WEAPON, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.literal("Enable the spawn of elite mob variants"),
                        ServerConfig.enableElite)
                .setDefaultValue(true)
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.ENABLE_ELITE, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Base Elite Chance"),
                        ServerConfig.baseEliteChance)
                .setDefaultValue(0.03F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(Component.literal("Increases the base chance of elite enemies (the \"Difficulty\" parameter also affects this), with a value of 1.0, the chance of elite will be 100%"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.BASE_ELITE_CHANCE, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startIntField(
                        Component.literal("Increase the maximum number of mobs"),
                        ServerConfig.addMoreMobs)
                .setDefaultValue(0)
                .setMin(0)
                .setMax(500)
                .setTooltip(Component.literal("Increases the maximum number of enemies that can spawn around the player, can greatly affect performance at high values."))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.ADD_MORE_MOBS, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.literal("Add new mobs ").append(
                                Component.literal("(Requires restarting the game)")
                                        .withStyle(style -> style.withColor(0xFF5555))
                        ),
                        ServerConfig.addNewMobsInSpawnPool)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Add new mobs in spawn pool(cave spider, parched, stray, husk)"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.ADD_NEW_MOBS, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.literal("Creeper less damage to monsters"),
                        ServerConfig.creeperDoesLessDamageToMonsters)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Creeper does less damage to monsters with his explosion"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.CREEPER_LESS_DAMAGE, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startFloatField(
                        Component.literal("Creeper disable shield time"),
                        ServerConfig.creeperDisableShieldTime)
                .setDefaultValue(3.5F)
                .setMax(100.0F)
                .setMin(0.0F)
                .setTooltip(Component.literal("the time in seconds for which the creeper turns off the shield during an explosion, setting to 0.0 will completely disable this mechanic."))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.CREEPER_DISABLE_TIME, val);
                })
                .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle(
                        Component.literal("Increase shield durability"),
                        ServerConfig.shiedIncreaseDurability)
                .setDefaultValue(true)
                .setTooltip(Component.literal("Increases shield strength by 3-4 times"))
                .setSaveConsumer(val -> {
                    ServerConfig.changeVariable(ServerConfig.ConfigField.SHIELD_INCREASE_DURABILITY, val);
                })
                .build()
        );

        return builder.build();
    }
}