package net.environmentz.network;

import net.environmentz.access.TemperatureManagerAccess;
import net.environmentz.network.packet.AffectionPacket;
import net.environmentz.network.packet.SyncValuesPacket;
import net.environmentz.network.packet.TemperaturePacket;
import net.environmentz.network.packet.ThermometerPacket;
import net.environmentz.temperature.TemperatureManager;
import net.environmentz.temperature.Temperatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class EnvironmentClientPacket {

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(AffectionPacket.PACKET_ID, (payload, context) -> {
            boolean isHotAffected = payload.heatAffected();
            boolean isColdAffected = payload.coldAffected();
            context.client().execute(() -> {
                TemperatureManager temperatureManager = ((TemperatureManagerAccess) context.player()).getTemperatureManager();
                temperatureManager.setEnvironmentAffection(isHotAffected, isColdAffected);
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(TemperaturePacket.PACKET_ID, (payload, context) -> {
            int temperature = payload.temperature();
            int wetness = payload.wetness();
            context.client().execute(() -> {
                TemperatureManager temperatureManager = ((TemperatureManagerAccess)  context.player()).getTemperatureManager();

                temperatureManager.setPlayerTemperature(temperature);
                temperatureManager.setPlayerWetIntensityValue(wetness);
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(ThermometerPacket.PACKET_ID, (payload, context) -> {
            int temperature = payload.temperature();
            context.client().execute(() -> {
                TemperatureManager temperatureManager = ((TemperatureManagerAccess)  context.player()).getTemperatureManager();
                temperatureManager.setThermometerTemperature(temperature);
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(SyncValuesPacket.PACKET_ID, (payload, context) -> {
            int max_very_cold = payload.max_very_cold();
            int max_cold = payload.max_cold();
            int min_cold = payload.min_cold();
            int normal = payload.normal();
            int min_hot = payload.min_hot();
            int max_hot = payload.max_hot();
            int max_very_hot = payload.max_very_hot();

            int wetness_max = payload.wetness_max();
            int wetness_soaked = payload.wetness_soaked();
            int wetness_water = payload.wetness_water();
            int wetness_rain = payload.wetness_rain();
            int wetness_dry = payload.wetness_dry();

            int very_cold = payload.very_cold();
            int cold = payload.cold();
            int hot = payload.hot();
            int very_hot = payload.very_hot();
            context.client().execute(() -> {
                Temperatures.setBodyTemperatures(max_very_cold, max_cold, min_cold, normal, min_hot, max_hot, max_very_hot);
                Temperatures.setBodyWetness(wetness_max, wetness_soaked, wetness_water, wetness_rain, wetness_dry);
                Temperatures.setThermometerTemperatures(very_cold, cold, hot, very_hot);
            });
        });
    }
}
