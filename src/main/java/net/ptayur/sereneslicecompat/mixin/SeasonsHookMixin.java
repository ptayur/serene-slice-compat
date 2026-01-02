package net.ptayur.sereneslicecompat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import com.possible_triangle.sliceanddice.block.sprinkler.WetAir;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(sereneseasons.season.SeasonHooks.class)
public abstract class SeasonsHookMixin {

    @Inject(method = "isRainingAtHook", at = @At("RETURN"), cancellable = true, remap = false)
    private static void isRainingAtHook(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
        if (!callback.getReturnValue() && WetAir.check(level, pos)) {
            callback.setReturnValue(true);
        }
    }
}
