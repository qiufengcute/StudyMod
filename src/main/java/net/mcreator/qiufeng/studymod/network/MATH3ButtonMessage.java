package net.mcreator.qiufeng.studymod.network;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.qiufeng.studymod.procedures.WAProcedure;
import net.mcreator.qiufeng.studymod.procedures.ACProcedure;
import net.mcreator.qiufeng.studymod.StudymodMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public record MATH3ButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {

	public static final Type<MATH3ButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, "math_3_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, MATH3ButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, MATH3ButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new MATH3ButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));
	@Override
	public Type<MATH3ButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final MATH3ButtonMessage message, final ServerPlayNetworking.Context context) {
		context.server().execute(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z));
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			WAProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			WAProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ACProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			WAProcedure.execute(world, x, y, z, entity);
		}
	}
}