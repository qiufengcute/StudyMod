package net.mcreator.qiufeng.studymod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.qiufeng.studymod.network.StudymodModVariables;

public class GetCanCloseTextProcedure {
	public static boolean eventResult = true;

	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if (!entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).IsOnExercise) {
			return Component.translatable("getcanclosetext.can").getString();
		}
		return "";
	}
}