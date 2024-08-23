package net.environmentz.init;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;

public class LootInit {

    public static void init() {

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if ("minecraft:entities/wolf".equals(key.getValue().toString())) {
                LootPool pool = LootPool.builder().with(ItemEntry.builder(ItemInit.WOLF_PELT_ITEM).build()).rolls(BinomialLootNumberProvider.create(3, 0.6F)).build();
                tableBuilder.pool(pool);
            }
            if ("minecraft:entities/polar_bear".equals(key.getValue().toString())) {
                LootPool pool = LootPool.builder().with(ItemEntry.builder(ItemInit.POLAR_BEAR_FUR_ITEM).build()).rolls(BinomialLootNumberProvider.create(2, 0.5F)).build();
                tableBuilder.pool(pool);
            }
        });
    }

}
