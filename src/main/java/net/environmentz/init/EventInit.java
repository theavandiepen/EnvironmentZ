package net.environmentz.init;

import net.environmentz.access.PlayerEnvAccess;
import net.environmentz.access.TemperatureManagerAccess;
import net.environmentz.network.EnvironmentServerPacket;
import net.environmentz.temperature.TemperatureManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;

public class EventInit {

    public static void init() {
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            ((PlayerEnvAccess) player).compatSync();
        });
        FabricBrewingRecipeRegistryBuilder.BUILD.register((builder) -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.FIRE_CHARGE, ItemInit.COLD_RESISTANCE);
            builder.registerPotionRecipe(ItemInit.COLD_RESISTANCE, Items.REDSTONE, ItemInit.LONG_COLD_RESISTANCE);
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SNOWBALL, ItemInit.OVERHEATING_RESISTANCE);
            builder.registerPotionRecipe(ItemInit.OVERHEATING_RESISTANCE, Items.REDSTONE, ItemInit.LONG_OVERHEATING_RESISTANCE);
        });
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server)->{
            TemperatureManager temperatureManager = ((TemperatureManagerAccess) handler.player).getTemperatureManager();

            EnvironmentServerPacket.writeS2CSyncEnvPacket(handler.player, temperatureManager.isHotEnvAffected(), temperatureManager.isColdEnvAffected());
            EnvironmentServerPacket.writeS2CTemperaturePacket(handler.player, temperatureManager.getPlayerTemperature(), temperatureManager.getPlayerWetIntensityValue());
            EnvironmentServerPacket.writeS2CSyncValuesPacket(handler.player);
        });
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive)->{
            TemperatureManager temperatureManager = ((TemperatureManagerAccess) oldPlayer).getTemperatureManager();

            EnvironmentServerPacket.writeS2CSyncEnvPacket(newPlayer, temperatureManager.isHotEnvAffected(), temperatureManager.isColdEnvAffected());
        });
    }
}
