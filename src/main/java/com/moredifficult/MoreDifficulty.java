package com.moredifficult;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MoreDifficulty implements ModInitializer {
	public static float difficultNumbery = 0.5F;
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
		if (enable()) {
			SpawnModification.reduceSpawn();
			SpawnModification.addNewSpawn();
		}

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			difficultNumbery = DifficultyState.getWorldDifficultyData(server).getWorldDifficulty();
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
			dispatcher.register(Commands.literal("setDifficulty")
					.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.then(Commands.argument("difficulty", FloatArgumentType.floatArg(0.0F, 100.F))
							.executes(context -> {
								difficultNumbery = FloatArgumentType.getFloat(context, "difficulty");
								context.getSource().sendSuccess(
										() -> Component.literal("The difficulty is set to " + difficultNumbery),
										false
								);
								MinecraftServer server = context.getSource().getServer();
								DifficultyState data = DifficultyState.getWorldDifficultyData(server);
								data.setWorldDifficulty(difficultNumbery);
								return 1;
							})
					)
			);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
			dispatcher.register(Commands.literal("getDifficulty")
					.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
					.executes(context -> {
						MinecraftServer server = context.getSource().getServer();
						DifficultyState data = DifficultyState.getWorldDifficultyData(server);
						context.getSource().sendSuccess(
								() -> Component.literal("The difficulty is " + data.getWorldDifficulty() + " now"),
								false
						);
						return 1;
					})
			);
		});
	}

	public static boolean enable(){
		return difficultNumbery != 0.0F;
	}
}