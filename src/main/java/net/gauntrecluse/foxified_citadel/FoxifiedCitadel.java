package net.gauntrecluse.foxified_citadel;

import com.mojang.logging.LogUtils;
import net.gauntrecluse.foxified_citadel.config.CommonConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import org.slf4j.Logger;

/**
 * Foxified Citadel, a completion and publication of Citadel's NeoForge 1.21.1 port.
 * @author GauntRecluse and Contributors - Foxified Citadel <br>
 * AlexModGuy and Contributors  - Original Citadel mod
 * @version 0.0.0-FOX <br>
 * I will probably forget to update the version here.
 */
@Mod(FoxifiedCitadel.MODID)
public class FoxifiedCitadel {
    public static final String MODID = "foxified_citadel";
    //Made Logger public to make it more widely usable elsewhere.
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public FoxifiedCitadel(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Foxified Citadel loading...");
        //Put the code after this logger
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.COMMON_SPEC);
        modEventBus.addListener(this::onConfigLoad);
        modEventBus.addListener(this::onConfigReload);
        //Put the code before this logger
        LOGGER.info("Initial Foxified Citadel loading complete.");
    }


    private void onConfigLoad(ModConfigEvent.Loading event) {
        LOGGER.info("Loading Foxified Citadel common config...");
        ModConfig config = event.getConfig();
        CommonConfig.isSkippingWarnings = CommonConfig.skipDatapackWarnings.get();
        CommonConfig.isAprilFoolsEnabled = CommonConfig.aprilFoolsContent.get();
        CitadelConstants.isForcingAprilFools = CommonConfig.forceAprilFoolsContent.get();
        CommonConfig.chunkGenModifierVal = CommonConfig.chunkGenSpawnModifier.get();
        CommonConfig.isCitadelEntityTracking = CommonConfig.citadelEntityTracker.get();
    }

    private void onConfigReload(ModConfigEvent.Reloading event) {
        LOGGER.info("Reloading Foxified Citadel common config...");
        ModConfig config = event.getConfig();
        CommonConfig.isSkippingWarnings = CommonConfig.skipDatapackWarnings.get();
        if(config.getSpec() == CommonConfig.COMMON_SPEC) {
            CommonConfig.isAprilFoolsEnabled = CommonConfig.aprilFoolsContent.get();
            CitadelConstants.isForcingAprilFools = CommonConfig.forceAprilFoolsContent.get();
            CommonConfig.chunkGenModifierVal = CommonConfig.chunkGenSpawnModifier.get();
            CommonConfig.isCitadelEntityTracking = CommonConfig.citadelEntityTracker.get();
        }
        //Check that values were properly assigned within logs for debugging purposes.
        LOGGER.info("Is skipping warnings: {}", CommonConfig.isSkippingWarnings);
        LOGGER.info("Is April Fools enabled: {}", CommonConfig.isAprilFoolsEnabled);
        LOGGER.info("Is Citadel tracking entities: {}", CommonConfig.isCitadelEntityTracking);
        LOGGER.info("Chunk gen modifier value: {}", CommonConfig.chunkGenModifierVal);
        LOGGER.info("Is April Fools forced: {}", CitadelConstants.isForcingAprilFools);
        LOGGER.info("Foxified Citadel Mod config reloaded!");
    }
}
