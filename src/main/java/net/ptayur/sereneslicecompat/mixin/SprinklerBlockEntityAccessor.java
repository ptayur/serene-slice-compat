package net.ptayur.sereneslicecompat.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockEntity.class)
public interface SprinklerBlockEntityAccessor {

    @Accessor("level")
    Level getLevel();

    @Accessor("worldPosition")
    BlockPos getPos();
}

