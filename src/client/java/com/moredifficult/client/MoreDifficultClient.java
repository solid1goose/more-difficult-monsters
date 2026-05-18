package com.moredifficult.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;

public class MoreDifficultClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, context) -> {
			dispatcher.register(ClientCommands.literal("moreDifficultMonstersConfig")
					.executes(ctx -> {
						Minecraft mc = Minecraft.getInstance();
						mc.execute(() -> mc.setScreen(ClientWindowConfig.createConfigScreen(mc.screen)));
						return 1;
					})
			);
		});
	}
}