package net.environmentz.network.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ThermometerPacket( int temperature) implements CustomPayload {

    public static final CustomPayload.Id<ThermometerPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of("environmentz", "thermometer_packet"));

    public static final PacketCodec<RegistryByteBuf, ThermometerPacket> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeInt(value.temperature());
    }, buf -> new ThermometerPacket(buf.readInt()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }

}

