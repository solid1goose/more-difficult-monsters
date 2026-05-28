package com.moredifficult.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MoreDifficultClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, context) -> {
			dispatcher.register(ClientCommands.literal("moreDifficultMonstersConfig")
					.executes(ctx -> {
						Minecraft mc = Minecraft.getInstance();
						if (!FabricLoader.getInstance().isModLoaded("cloth-config")) {
                            assert mc.player != null;
                            mc.player.sendSystemMessage(Component.literal("Please install Cloth Config to use this command!"));
							return 1;
						}
						mc.execute(() -> mc.setScreen(ClientWindowConfig.createConfigScreen(mc.screen)));
						return 1;
					})
			);
		});
	}
}