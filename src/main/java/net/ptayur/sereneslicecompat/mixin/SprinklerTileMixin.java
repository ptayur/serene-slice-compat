package net.ptayur.sereneslicecompat.mixin;

import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerTile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.ptayur.sereneslicecompat.sound.WetAirSound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SprinklerTile.class)
public abstract class SprinklerTileMixin {

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerTile;spawnProcessingParticles(Lnet/minecraftforge/fluids/FluidStack;)V"
            ),
            remap = false
    )
    private void tick(CallbackInfo callback) {
        BlockEntityAccessor acc = (BlockEntityAccessor) this;
        Level level = acc.sereneseasonscompat$getLevel();

        if (!(level instanceof ClientLevel client)) return;

        BlockPos pos = acc.sereneseasonscompat$getPos();
        WetAirSound.tryPlayWetAirSound(client, pos);
    }
}
