package net.environmentz.init;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.environmentz.effect.*;

public class EffectInit {
    public final static RegistryEntry<StatusEffect> WARMING = register("environmentz:warming", new WarmEffect(StatusEffectCategory.BENEFICIAL, 16771455));
    public final static RegistryEntry<StatusEffect> COOLING = register("environmentz:cooling",new CoolEffect(StatusEffectCategory.BENEFICIAL, 6541055));
    public final static RegistryEntry<StatusEffect> COMFORT = register("environmentz:comfort",new ComfortEffect(StatusEffectCategory.BENEFICIAL, 0xE8732D));

    public static void init() {
    }

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(id), statusEffect);
    }

}
