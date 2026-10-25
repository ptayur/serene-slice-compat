package net.ptayur.sereneslicecompat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import com.possible_triangle.atmosphere.api.v1.WeatherAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(sereneseasons.season.SeasonHooks.class)
public abstract class SeasonHooksMixin {

    @Inject(method = "isRainingAtHook", at = @At("RETURN"), cancellable = true, remap = false)
    private static void isRainingAtHook(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        if (!callback.getReturnValue()) {
            var weather = WeatherAPI.INSTANCE.getWeather(level);
            var condition = weather.atPositionOrDefault(
                    level,
                    Vec3.atCenterOf(pos)
            );

            if (condition.value().precipitation() == Biome.Precipitation.RAIN) {
                callback.setReturnValue(true);
            }
        }
    }
}
