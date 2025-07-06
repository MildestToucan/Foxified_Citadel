package net.gauntrecluse.foxified_citadel.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Renamed to {@code CommonConfig}, original mod registers it as a common config in spite of naming it {@code ServerConfig}. <br>
 * Does not use the config structure relating to Citadel's ConfigHolder class.
 */
//TODO: Thoroughly check once there are events which use these.
public class CommonConfig {
    public static final ModConfigSpec COMMON_SPEC;

    //Config values and their associated variables.
    public static ModConfigSpec.BooleanValue citadelEntityTracker;
    public static boolean isCitadelEntityTracking;

    public static ModConfigSpec.BooleanValue skipDatapackWarnings;
    public static boolean isSkippingWarnings;

    public static ModConfigSpec.DoubleValue chunkGenSpawnModifier;
    public static double chunkGenModifierVal = 1.0F;

    public static ModConfigSpec.BooleanValue aprilFoolsContent;
    public static boolean isAprilFoolsEnabled;

    /**
     * A config option used for both debugging and to allow users to force any of the checks that use the calendar check in {@link net.gauntrecluse.foxified_citadel.CitadelConstants} to return true. <br>
     * This is original to FOX.
     */
    public static ModConfigSpec.BooleanValue forceAprilFoolsContent;
    //The associated boolean is in CitadelConstants rather than here.

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        citadelEntityTracker = COMMON_BUILDER.comment("True if citadel tracks entity properties(freezing, stone mobs, etc) on server. Turn this to false to solve some server lag, may break some stuff.")
                .define("isCitadelTrackingEntities", true);

        skipDatapackWarnings = COMMON_BUILDER.comment("True to skip warnings about using datapacks")
                .define("isSkippingDatapackWarnings", true);

        chunkGenSpawnModifier = COMMON_BUILDER.comment("Multiplies the count of entities spawned by this number. 0 = no entites added on chunk gen, 2 = twice as many entities added on chunk gen. Useful for many mods that add a lot of creatures, namely animals, to the spawn lists.")
                .defineInRange("chunkGenSpawnModifier", 1.0F, 0.0F, 100000F);

        aprilFoolsContent = COMMON_BUILDER.comment("True if april fools content can be displayed on April Fools")
                .define("isAprilFoolsAllowed", true);

        forceAprilFoolsContent = COMMON_BUILDER.comment("True if the game should return all Citadel-based april fools checks as true. Effectively enables April Fools year-long.")
                .define("forceAprilFoolsContent", false);

        COMMON_SPEC = COMMON_BUILDER.build();
    }
}
