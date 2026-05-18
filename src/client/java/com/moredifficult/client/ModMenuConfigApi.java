package com.moredifficult.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.Minecraft;

public class ModMenuConfigApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Minecraft mc = Minecraft.getInstance();
            return ClientWindowConfig.createConfigScreen(mc.screen);
        };
    }
}
