package net.mcreator.qiufeng.studymod.item;

import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import java.util.function.Consumer;

public class WuSanSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 99999, 99999f, 0, 20, TagKey.create(Registries.ITEM, ResourceLocation.parse("studymod:wu_san_sword_repair_items")));

	public WuSanSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 99998f, 95f).fireResistant());
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> componentConsumer, TooltipFlag flag) {
		super.appendHoverText(itemstack, context, tooltipDisplay, componentConsumer, flag);
		componentConsumer.accept(Component.translatable("item.studymod.wu_san_sword.description_0"));
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}