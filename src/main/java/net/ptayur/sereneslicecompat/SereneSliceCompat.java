package net.ptayur.sereneslicecompat;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SereneSliceCompat.MOD_ID)
public class SereneSliceCompat
{
    public static final String MOD_ID = "sereneslicecompat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SereneSliceCompat(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
