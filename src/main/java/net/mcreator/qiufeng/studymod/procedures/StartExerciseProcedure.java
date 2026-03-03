package net.mcreator.qiufeng.studymod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.qiufeng.studymod.world.inventory.*;
import net.mcreator.qiufeng.studymod.network.StudymodModVariables;

import io.netty.buffer.Unpooled;

public class StartExerciseProcedure {
	public static boolean eventResult = true;

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		{
			StudymodModVariables.PlayerVariables _vars = entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES);
			_vars.IsOnExercise = true;
			_vars.markSyncDirty();
		}
		if (world instanceof ServerLevel _level) {
			itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
			});
		}
		{
			StudymodModVariables.PlayerVariables _vars = entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES);
			_vars.TempRandomNumber = Mth.nextInt(RandomSource.create(), 1, 3);
			_vars.markSyncDirty();
		}
		if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 1) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos3 = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("CN1");
					}

					@Override
					public boolean shouldCloseCurrentScreen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new CN1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos3));
					}
				});
			}
		} else if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 2) {
			{
				StudymodModVariables.PlayerVariables _vars = entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES);
				_vars.TempRandomNumber = Mth.nextInt(RandomSource.create(), 1, 4);
				_vars.markSyncDirty();
			}
			if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 1) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos5 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("MATH1");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new MATH1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos5));
						}
					});
				}
			} else if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 2) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos6 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("MATH2");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new MATH2Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos6));
						}
					});
				}
			} else if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 3) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos7 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("MATH3");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new MATH3Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos7));
						}
					});
				}
			} else {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos8 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("MATH4");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new MATH4Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos8));
						}
					});
				}
			}
		} else {
			{
				StudymodModVariables.PlayerVariables _vars = entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES);
				_vars.TempRandomNumber = Mth.nextInt(RandomSource.create(), 1, 3);
				_vars.markSyncDirty();
			}
			if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 1) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos10 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("EN1");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new EN1Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos10));
						}
					});
				}
			} else if (entity.getAttachedOrCreate(StudymodModVariables.PLAYER_VARIABLES).TempRandomNumber == 2) {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos11 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("EN2");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new EN2Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos11));
						}
					});
				}
			} else {
				if (entity instanceof ServerPlayer _ent) {
					BlockPos _bpos12 = BlockPos.containing(x, y, z);
					_ent.openMenu(new MenuProvider() {
						@Override
						public Component getDisplayName() {
							return Component.literal("EN3");
						}

						@Override
						public boolean shouldCloseCurrentScreen() {
							return false;
						}

						@Override
						public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
							return new EN3Menu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos12));
						}
					});
				}
			}
		}
	}
}