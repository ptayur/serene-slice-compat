package net.ptayur.sereneslicecompat.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.ptayur.sereneslicecompat.SereneSliceCompat;

import java.lang.reflect.Method;

public class SliceAndDiceCompat {
    private static Method wetAirCheck;
    private static boolean available;

    private static boolean initialized;

    public static void init() {
        if (initialized) return;
        initialized = true;

        try {
            Class<?> wetAirClass = Class.forName(
                    "com.possible_triangle.sliceanddice.block.sprinkler.WetAir"
            );

            wetAirCheck = wetAirClass.getDeclaredMethod(
                    "check",
                    Level.class,
                    BlockPos.class
            );
            wetAirCheck.setAccessible(true);
            available = true;

            SereneSliceCompat.LOGGER.warn("Slice & Dice WetAir integration succeeded");

        } catch (Throwable t) {
            available = false;
            SereneSliceCompat.LOGGER.warn("Slice & Dice WetAir integration failed", t);
        }
    }

    public static boolean isWetAir(Level level, BlockPos pos) {
        init();
        if (!available) return false;
        if (level.isClientSide) return false;

        try {
            SereneSliceCompat.LOGGER.error("WetAir.check invocation try");
            return (boolean) wetAirCheck.invoke(null, level, pos);
        } catch (Throwable t) {
            SereneSliceCompat.LOGGER.error("WetAir.check invocation failed", t);
            return false;
        }
    }

}
