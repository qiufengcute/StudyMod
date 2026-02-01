/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.qiufeng.studymod.init;

import net.minecraft.world.item.CreativeModeTabs;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class StudymodModTabs {
	public static void load() {
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(tabData -> {
			tabData.accept(StudymodModItems.WU_SAN_EXERCISE);
		});
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(tabData -> {
			tabData.accept(StudymodModItems.WU_SAN_SWORD);
		});
	}
}