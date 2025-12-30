package net.ptayur.sereneslicecompat.sound;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WetAirSound {

    private static long lastPlayTick = 0;

    public static void tryPlayWetAirSound(ClientLevel level, BlockPos pos) {
        long gameTime = level.getGameTime();

        if (gameTime - lastPlayTick < 20) return;

        lastPlayTick = gameTime;

        level.playLocalSound(
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                SoundEvents.WEATHER_RAIN,
                SoundSource.WEATHER,
                0.35F,
                1.0F,
                false
        );
    }
}

