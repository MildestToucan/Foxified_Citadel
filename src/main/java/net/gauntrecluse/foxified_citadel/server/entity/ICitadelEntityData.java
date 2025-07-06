package net.gauntrecluse.foxified_citadel.server.entity;

import net.minecraft.nbt.CompoundTag;

/**
 * @author Alexthe666
 * @since 1.7.0
 */
public interface ICitadelEntityData {
    CompoundTag getCitadelEntityData();

    void setCitadelEntityData(CompoundTag nbt);
}
