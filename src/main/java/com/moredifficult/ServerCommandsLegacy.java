package com.moredifficult;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.permissions.Permissions;

public class ServerCommandsLegacy {
    public static void registerLegacyServerCommands(){
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {
            dispatcher.register(Commands.literal("setDifficulty")
                    .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR))
                    .then(Commands.argument("difficulty", FloatArgumentType.floatArg(0.0F, 100.F))
                            .executes(context -> {
                                ServerConfig.difficultNumbery = FloatArgumentType.getFloat(context, "difficulty");
                                context.getSource().sendSuccess(
                                        () -> Component.literal("The difficulty is set to " + ServerConfig.difficultNumbery),
                                        false
                                );
                                ServerConfig.save();
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
                        context.getSource().sendSuccess(
                                () -> Component.literal("The difficulty is " + ServerConfig.difficultNumbery + " now"),
                                false
                        );
                        return 1;
                    })
            );
        });
    }
}
