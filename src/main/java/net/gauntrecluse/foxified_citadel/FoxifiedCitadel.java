package net.gauntrecluse.foxified_citadel;

import com.mojang.logging.LogUtils;
import net.gauntrecluse.foxified_citadel.config.CommonConfig;
import net.gauntrecluse.foxified_citadel.network.payloads.PropertiesMessage;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.slf4j.Logger;

import java.util.function.Supplier;

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
    public static ServerProxy PROXY = unsafeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    public FoxifiedCitadel(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Foxified Citadel loading...");
        //Put the code after this logger
        modContainer.registerConfig(ModConfig.Type.COMMON, CommonConfig.COMMON_SPEC);
        modEventBus.addListener(this::onConfigLoad);
        modEventBus.addListener(this::onConfigReload);
        modEventBus.addListener(this::registerPayloads);
        //Put the code before this logger
        LOGGER.info("Initial Foxified Citadel loading complete.");
    }


    private void onConfigLoad(ModConfigEvent.Loading event) {
        LOGGER.info("Loading Foxified Citadel common config...");
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


    public void registerPayloads(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("citadel").versioned("1").optional();
        registrar.playToServer(PropertiesMessage.TYPE, PropertiesMessage.CODEC, PropertiesMessage::handle);
    }



    private static <T> T unsafeRunForDist(Supplier<Supplier<T>> clientTarget, Supplier<Supplier<T>> serverTarget) {
        return switch(FMLEnvironment.dist) { //Changed to "enhanced" switch statement as it is subjectively cleaner -GauntRecluse
            case CLIENT -> clientTarget.get().get();
            case DEDICATED_SERVER -> serverTarget.get().get();
//            default -> throw new IllegalArgumentException("UNSIDED?"); Commented out unless an issue comes up,
            //The only two possible cases are CLIENT and DEDICATED_SERVER, so "default" is unreachable.
        };
    }
}
