package com.moredifficult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerConfig {
    public enum ConfigField {
        DIFFICULTY("difficultNumbery"),
        BASE_ARMOR_CHANCE("baseChanceForMobArmor"),
        INCREASE_MONSTER_ARMOR("increaseMonsterArmor"),
        BASE_WEAPON_CHANCE("baseChanceForMobWeapon"),
        INCREASE_MONSTER_WEAPON("increaseMonsterWeapon"),
        ENABLE_ELITE("enableElite"),
        BASE_ELITE_CHANCE("baseEliteChance"),
        ADD_MORE_MOBS("addMoreMobs"),
        ADD_NEW_MOBS("addNewMobsInSpawnPool"),
        CREEPER_LESS_DAMAGE("creeperDoesLessDamageToMonsters"),
        CREEPER_DISABLE_TIME("creeperDisableShieldTime"),
        SHIELD_INCREASE_DURABILITY("shiedIncreaseDurability");

        public final String fieldName;
        ConfigField(String fieldName) {
            this.fieldName = fieldName;
        }
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("more-difficult-monsters.json");

    public static float difficultNumbery = 1.0F;
    public static float baseChanceForMobArmor = 0.15F;
    public static int increaseMonsterArmor = 0;
    public static float baseChanceForMobWeapon = 0.1F;
    public static float increaseMonsterWeapon = 0.0F;
    public static boolean enableElite = true;
    public static float baseEliteChance = 0.03F;
    public static int addMoreMobs = 0;
    public static boolean addNewMobsInSpawnPool = true;
    public static boolean creeperDoesLessDamageToMonsters = true;
    public static float creeperDisableShieldTime = 3.5F;
    public static boolean shiedIncreaseDurability = true;

    public static void changeVariable(ConfigField field, Object value) {
        try {
            Field f = ServerConfig.class.getField(field.fieldName);
            if (f.getType() == float.class)
                f.set(null, ((Number) value).floatValue());
            else if (f.getType() == int.class)
                f.set(null, ((Number) value).intValue());
            else if (f.getType() == boolean.class)
                f.set(null, (Boolean) value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        save();
    }

    public static void save() {
        JsonObject json = new JsonObject();
        for (Field f : ServerConfig.class.getFields()) {
            try {
                Object val = f.get(null);
                if (val instanceof Float v) json.addProperty(f.getName(), v);
                else if (val instanceof Integer v) json.addProperty(f.getName(), v);
                else if (val instanceof Boolean v) json.addProperty(f.getName(), v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            for (Field f : ServerConfig.class.getFields()) {
                if (!json.has(f.getName())) continue;
                try {
                    if (f.getType() == float.class)
                        f.set(null, json.get(f.getName()).getAsFloat());
                    else if (f.getType() == int.class)
                        f.set(null, json.get(f.getName()).getAsInt());
                    else if (f.getType() == boolean.class)
                        f.set(null, json.get(f.getName()).getAsBoolean());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}