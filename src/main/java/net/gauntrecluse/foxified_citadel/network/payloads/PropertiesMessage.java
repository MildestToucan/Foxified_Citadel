package net.gauntrecluse.foxified_citadel.network.payloads;

import io.netty.buffer.ByteBuf;
import net.gauntrecluse.foxified_citadel.FoxifiedCitadel;
import net.gauntrecluse.foxified_citadel.network.PacketBufferUtils;
import net.gauntrecluse.foxified_citadel.server.entity.CitadelEntityData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record PropertiesMessage(String propertyID, CompoundTag compound, int entityID) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PropertiesMessage>
            TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("citadel", "properties"));

    public static final StreamCodec<FriendlyByteBuf, PropertiesMessage> CODEC = StreamCodec.ofMember(PropertiesMessage::write, PropertiesMessage::read);

    public static void write(PropertiesMessage message, FriendlyByteBuf packetBuffer) {
        PacketBufferUtils.writeUTF8String(packetBuffer, message.propertyID);
        PacketBufferUtils.writeTag(packetBuffer, message.compound);
        packetBuffer.writeInt(message.entityID);
    }

    public static PropertiesMessage read(FriendlyByteBuf packetBuffer) {
        return new PropertiesMessage(PacketBufferUtils.readUTF8String(packetBuffer), PacketBufferUtils.readTag(packetBuffer), packetBuffer.readInt());
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(final PropertiesMessage message, IPayloadContext context) {
        context.enqueueWork(() -> {
            if(context.flow().isClientbound()) {
                FoxifiedCitadel.PROXY.handlePropertiesPacket(message.propertyID, message.compound, message.entityID);
            } else {
                Entity e = context.player().level().getEntity(message.entityID);
                if(e instanceof LivingEntity && (message.propertyID.equals("CitadelPatreonConfig") || message.propertyID.equals("CitadelTagUpdate"))) {
                    CitadelEntityData.setCitadelTag((LivingEntity) e, message.compound);
                }
            }
        });
    }
}
