package net.environmentz.mixin;

import java.util.List;

import net.environmentz.init.ItemInit;
import net.minecraft.item.tooltip.TooltipType;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(ArmorItem.class)
public class ArmorItemMixin extends Item {

    public ArmorItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        if (stack.get(ItemInit.INSULATED) != null && stack.get(ItemInit.INSULATED)) {
            tooltip.add(Text.translatable("item.environmentz.fur_insolated.tooltip").formatted(Formatting.BLUE));
        }
        if (stack.get(ItemInit.ICED) != null && stack.get(ItemInit.ICED) > 0) {
            tooltip.add(Text.translatable("item.environmentz.iced.tooltip", stack.get(ItemInit.ICED)).formatted(Formatting.BLUE));
        }
    }
}
