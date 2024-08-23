package net.environmentz.network;

import net.environmentz.network.packet.AffectionPacket;
import net.environmentz.network.packet.SyncValuesPacket;
import net.environmentz.network.packet.TemperaturePacket;
import net.environmentz.network.packet.ThermometerPacket;
import net.environmentz.temperature.Temperatures;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class EnvironmentServerPacket {

    public static void init() {
        PayloadTypeRegistry.playS2C().register(AffectionPacket.PACKET_ID, AffectionPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(TemperaturePacket.PACKET_ID, TemperaturePacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(ThermometerPacket.PACKET_ID, ThermometerPacket.PACKET_CODEC);
        PayloadTypeRegistry.playS2C().register(SyncValuesPacket.PACKET_ID, SyncValuesPacket.PACKET_CODEC);
    }

    public static void writeS2CSyncEnvPacket(ServerPlayerEntity serverPlayerEntity, boolean heatAffected, boolean coldAffected) {
        ServerPlayNetworking.send(serverPlayerEntity, new AffectionPacket(heatAffected, coldAffected));
    }

    public static void writeS2CTemperaturePacket(ServerPlayerEntity serverPlayerEntity, int temperature, int wetness) {
        ServerPlayNetworking.send(serverPlayerEntity, new TemperaturePacket(temperature, wetness));
    }

    public static void writeS2CThermometerPacket(ServerPlayerEntity serverPlayerEntity, int temperature) {
        ServerPlayNetworking.send(serverPlayerEntity, new ThermometerPacket(temperature));
    }

    public static void writeS2CSyncValuesPacket(ServerPlayerEntity serverPlayerEntity) {
        ServerPlayNetworking.send(serverPlayerEntity, new SyncValuesPacket(Temperatures.getBodyTemperatures(0), Temperatures.getBodyTemperatures(1), Temperatures.getBodyTemperatures(2), Temperatures.getBodyTemperatures(3), Temperatures.getBodyTemperatures(4), Temperatures.getBodyTemperatures(5), Temperatures.getBodyTemperatures(6), Temperatures.getBodyWetness(0), Temperatures.getBodyWetness(1), Temperatures.getBodyWetness(2), Temperatures.getBodyWetness(3), Temperatures.getBodyWetness(4), Temperatures.getThermometerTemperatures(0), Temperatures.getThermometerTemperatures(1), Temperatures.getThermometerTemperatures(2), Temperatures.getThermometerTemperatures(3)));
    }
}
