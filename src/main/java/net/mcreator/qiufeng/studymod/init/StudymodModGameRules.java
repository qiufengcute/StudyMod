/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.qiufeng.studymod.init;

import net.minecraft.world.level.GameRules;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;

public class StudymodModGameRules {
	public static GameRules.Key<GameRules.BooleanValue> SUMMON_LIGHTING_ON_WRONG;

	public static void load() {
		SUMMON_LIGHTING_ON_WRONG = GameRuleRegistry.register("summonLightingOnWrong", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
	}
}