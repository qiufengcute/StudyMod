/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.qiufeng.studymod.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.mcreator.qiufeng.studymod.StudymodMod;

public class StudymodModSounds {
	public static SoundEvent CODELIGHTOGG;

	public static void load() {
		CODELIGHTOGG = register("codelightogg", SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("studymod", "codelightogg")));
	}

	private static SoundEvent register(String registryname, SoundEvent element) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, registryname), element);
	}
}