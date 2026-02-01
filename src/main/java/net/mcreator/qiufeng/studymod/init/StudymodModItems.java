/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.qiufeng.studymod.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.qiufeng.studymod.item.WuSanSwordItem;
import net.mcreator.qiufeng.studymod.item.WuSanExerciseItem;
import net.mcreator.qiufeng.studymod.StudymodMod;

import java.util.function.Function;

public class StudymodModItems {
	public static Item WU_SAN_EXERCISE;
	public static Item WU_SAN_SWORD;

	public static void load() {
		WU_SAN_EXERCISE = register("wu_san_exercise", WuSanExerciseItem::new);
		WU_SAN_SWORD = register("wu_san_sword", WuSanSwordItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> I register(String name, Function<Item.Properties, ? extends I> supplier) {
		return (I) Items.registerItem(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, name)), (Function<Item.Properties, Item>) supplier);
	}
}