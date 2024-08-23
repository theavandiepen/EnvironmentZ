package net.environmentz.network.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record AffectionPacket(boolean heatAffected, boolean coldAffected) implements CustomPayload {

    public static final CustomPayload.Id<AffectionPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of("environmentz", "affection_packet"));

    public static final PacketCodec<RegistryByteBuf, AffectionPacket> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeBoolean(value.heatAffected());
        buf.writeBoolean(value.coldAffected());
    }, buf -> new AffectionPacket(buf.readBoolean(), buf.readBoolean()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

}

