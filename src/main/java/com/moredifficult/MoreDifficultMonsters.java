package com.moredifficult;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.world.entity.EntityType;

public class MoreDifficultMonsters implements ModInitializer {
	public static final EntityType[] monsters = {
		EntityType.CREEPER,
		EntityType.SKELETON,
		EntityType.ZOMBIE,
		EntityType.SPIDER,
		EntityType.CAVE_SPIDER,
		EntityType.HUSK,
		EntityType.STRAY,
		EntityType.PARCHED
	};
	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			ServerConfig.load();
			if (ServerConfig.addNewMobsInSpawnPool) {
				System.out.println(ServerConfig.addNewMobsInSpawnPool);
				SpawnModification.modificationMobSpawn();
			}
		});

		ServerCommandsLegacy.registerLegacyServerCommands();
	}

	public static boolean enable(){
		return ServerConfig.difficultNumbery != 0.0F;
	}
}