package net.ptayur.sereneslicecompat.mixin;

import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.ptayur.sereneslicecompat.SereneSliceCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SprinklerBlockEntity.class)
public abstract class SprinklerBlockEntityMixin {

    @Unique
    private static boolean sereneSliceCompat$warningAccessor = false;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerBlockEntity;" +
                             "spawnProcessingParticles(Lnet/neoforged/neoforge/fluids/FluidStack;)V"
            ),
            remap = false
    )
    private void sereneSliceCompat$tick(CallbackInfo callback) {
        if (!(this instanceof SprinklerBlockEntityAccessor sprinklerBlockEntityAccessor)) {
            if (!sereneSliceCompat$warningAccessor) {
                sereneSliceCompat$warningAccessor = true;
                SereneSliceCompat.LOGGER.warn(
                        "SprinklerBlockEntityAccessor is not available in sereneSliceCompat$tick. " +
                        "Sprinkler sound will not be played."
                );
            }
            return;
        }

        Level level = sprinklerBlockEntityAccessor.getLevel();

        if (level == null || !level.isClientSide) return;

        BlockPos pos = sprinklerBlockEntityAccessor.getPos();
        float pitch = 0.8F + level.getRandom().nextFloat() * 0.2F;
        float volume = 0.02F;

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
