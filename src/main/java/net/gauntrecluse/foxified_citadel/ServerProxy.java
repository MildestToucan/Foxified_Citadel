package net.gauntrecluse.foxified_citadel;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = FoxifiedCitadel.MODID)
public class ServerProxy {

    public ServerProxy() {
    }

    public void onPreInit() {
    }

    public void handleAnimationPacket(int entityId, int index) {
    }

    public void handlePropertiesPacket(String propertyID, CompoundTag compound, int entityID) {
    }

    public void handleClientTickRatePacket(CompoundTag compound) {
    }

    //TODO: Finish this, currently only creating it to finish the payloads.
}
