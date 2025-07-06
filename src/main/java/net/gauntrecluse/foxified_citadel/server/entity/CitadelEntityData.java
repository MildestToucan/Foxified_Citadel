package net.gauntrecluse.foxified_citadel.server.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;

/**
 * CitadelTag is a datasynced tag for LivingEntity provided by Citadel to be used by various mods.
 * @author Alexthe666
 * @since 1.7.0
 */
public class CitadelEntityData {

    public static CompoundTag getOrCreateCitadelTag(LivingEntity entity) {
        CompoundTag tag = getCitadelTag(entity);
        return tag == null ? new CompoundTag() : tag; //If there is no tag, make one, else, return the existing tag.
    }

    public static CompoundTag getCitadelTag(LivingEntity entity) {
        return entity instanceof ICitadelEntityData ? ((ICitadelEntityData) entity).getCitadelEntityData() : new CompoundTag();
    }

    public static void setCitadelTag(LivingEntity entity, CompoundTag tag) {
        if(entity instanceof ICitadelEntityData) {
            ((ICitadelEntityData) entity).setCitadelEntityData(tag);
        }
    }
}
