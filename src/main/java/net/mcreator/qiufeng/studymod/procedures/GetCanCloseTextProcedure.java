package net.mcreator.qiufeng.studymod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;

import net.mcreator.qiufeng.studymod.network.StudymodModVariables;

public class GetCanCloseTextProcedure {
	public static boolean eventResult = true;

	public static String execute(LevelAccessor world) {
		if (!StudymodModVariables.MapVariables.get(world).IsOnExercise) {
			return Component.translatable("getcanclosetext.can").getString();
		}
		return "";
	}
}