package net.ptayur.sereneslicecompat.mixin;

import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerTile;
import net.minecraft.client.multiplayer.ClientLevel;
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


@Mixin(SprinklerTile.class)
public abstract class SprinklerTileMixin {

    @Unique
    private static boolean sereneSliceCompat$warningAccessor = false;
    @Unique
    private static boolean sereneSliceCompat$warningClientLevel = false;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerTile;" +
                             "spawnProcessingParticles(Lnet/minecraftforge/fluids/FluidStack;)V"
            ),
            remap = false
    )
    private void sereneSliceCompat$tick(CallbackInfo callback) {
        if (!(this instanceof SprinklerTileAccessor sprinklerTileAccessor)) {
            if (!sereneSliceCompat$warningAccessor) {
                sereneSliceCompat$warningAccessor = true;
                SereneSliceCompat.LOGGER.warn(
                        "SprinklerTileAccessor is not available in sereneSliceCompat$tick. " +
                        "Sprinkler sound will not be played."
                );
            }
            return;
        }

        Level level = sprinklerTileAccessor.getLevel();
        BlockPos pos = sprinklerTileAccessor.getPos();

        if (!(level instanceof ClientLevel clientLevel)) {
            if (!sereneSliceCompat$warningClientLevel) {
                sereneSliceCompat$warningClientLevel = true;
                SereneSliceCompat.LOGGER.warn(
                        "Level in sereneSliceCompat$tick is not a ClientLevel. " +
                        "Sprinkler sound will not be played."
                );
            }
            return;
        }

        float pitch = 0.8F + clientLevel.random.nextFloat() * 0.2F;
        float volume = 0.02F;

        clientLevel.playLocalSound(
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
