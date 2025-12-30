package net.ptayur.sereneslicecompat.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ptayur.sereneslicecompat.sound.WetAirSound;
import net.ptayur.sereneslicecompat.hook.WetAirHook;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientTickHandler {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;
        Player player = mc.player;

        if (level == null || player == null) return;

        BlockPos pos = player.blockPosition();

        if (WetAirHook.isWetAir(level, pos)) {
            WetAirSound.tryPlayWetAirSound(level, pos);
        }
    }
}

