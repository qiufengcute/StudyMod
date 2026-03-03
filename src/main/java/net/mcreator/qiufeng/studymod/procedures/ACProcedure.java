package net.mcreator.qiufeng.studymod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

import net.mcreator.qiufeng.studymod.network.StudymodModVariables;
import net.mcreator.qiufeng.studymod.init.StudymodModItems;

public class ACProcedure {
	public static boolean eventResult = true;

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).IsOnExercise) {
			if (entity instanceof Player _player) {
				_player.containerMenu = _player.inventoryMenu;
			}
			if (entity instanceof Player _player && !_player.level().isClientSide()) {
				_player.displayClientMessage(Component.literal("\u606D\u559C\uFF01\u7B54\u5BF9\u4E86"), true);
			}
			if (world instanceof ServerLevel _level) {
				ItemEntity entityToSpawn_2 = new ItemEntity(_level, x, y, z, new ItemStack(Items.NETHERITE_INGOT));
				entityToSpawn_2.setPickUpDelay(0);
				entityToSpawn_2.setUnlimitedLifetime();
				_level.addFreshEntity(entityToSpawn_2);
			}
			if (Mth.nextInt(RandomSource.create(), 1, 100) == 1) {
				if (world instanceof ServerLevel _level) {
					ItemEntity entityToSpawn_4 = new ItemEntity(_level, x, y, z, new ItemStack(StudymodModItems.WU_SAN_SWORD));
					entityToSpawn_4.setPickUpDelay(0);
					entityToSpawn_4.setUnlimitedLifetime();
					_level.addFreshEntity(entityToSpawn_4);
				}
			}
			if (entity instanceof Player _player) {
				_player.containerMenu = _player.inventoryMenu;
			}
		}
		{
			StudymodModVariables.PlayerVariables _vars = entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES);
			_vars.IsOnExercise = false;
			_vars.markSyncDirty();
		}
	}
}