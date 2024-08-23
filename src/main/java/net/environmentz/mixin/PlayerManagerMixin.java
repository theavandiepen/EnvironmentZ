package net.environmentz.mixin;

import com.mojang.authlib.GameProfile;
import net.environmentz.init.ConfigInit;
import net.environmentz.init.EffectInit;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.ClientConnection;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;onPlayerConnected(Lnet/minecraft/server/network/ServerPlayerEntity;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onPlayerConnectComfortMixin(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo info, GameProfile gameProfile, UserCache userCache, String string, Optional<NbtCompound> optional, RegistryKey registryKey, ServerWorld serverWorld, ServerWorld serverWorld2) {
        if (!optional.isPresent() && !player.isCreative() && ConfigInit.CONFIG.startUpComfortEffectDuration > 0) {
            player.addStatusEffect(new StatusEffectInstance(EffectInit.COMFORT, ConfigInit.CONFIG.startUpComfortEffectDuration, 0, false, false, true));
        }
    }
}
