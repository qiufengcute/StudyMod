package net.mcreator.qiufeng.studymod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.qiufeng.studymod.network.StudymodModVariables;
import net.mcreator.qiufeng.studymod.init.StudymodModGameRules;

public class WAProcedure {
	public static boolean eventResult = true;

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (StudymodModVariables.MapVariables.get(world).IsOnExercise) {
			if (entity instanceof Player _player && !_player.level().isClientSide()) {
				_player.displayClientMessage(Component.literal("\u5F88\u9057\u61BE \u7B54\u9519\u4E86"), true);
			}
			if (world instanceof ServerLevel _serverLevelGR1 && _serverLevelGR1.getGameRules().getBoolean(StudymodModGameRules.SUMMON_LIGHTING_ON_WRONG)) {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn_2 = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
					entityToSpawn_2.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
					_level.addFreshEntity(entityToSpawn_2);
				}
			}
			if (entity instanceof Player _player) {
				_player.containerMenu = _player.inventoryMenu;
			}
		}
		StudymodModVariables.MapVariables.get(world).IsOnExercise = false;
		StudymodModVariables.MapVariables.get(world).markSyncDirty();
	}
}