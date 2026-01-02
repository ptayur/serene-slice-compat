package net.ptayur.sereneslicecompat.sound;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class WetAirSound {

    private static final Random random = new Random();

    public static void tryPlayWetAirSound(ClientLevel level, BlockPos pos) {
        float pitch = 0.8F + random.nextFloat() * 0.2F;
        float volume = 0.04F;

        level.playLocalSound(
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                SoundEvents.WEATHER_RAIN_ABOVE,
                SoundSource.BLOCKS,
                volume,
                pitch,
                false
        );
    }
}

