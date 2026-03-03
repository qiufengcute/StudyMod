package net.mcreator.qiufeng.studymod.network;

import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.util.ProblemReporter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;

import net.mcreator.qiufeng.studymod.event.PlayerEvents;
import net.mcreator.qiufeng.studymod.StudymodMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.Codec;

public class StudymodModVariables {
	public static final AttachmentType<PlayerVariables> PLAYER_VARIABLES = AttachmentRegistry.create(ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, "player_variables"),
			(builder) -> builder.persistent(PlayerVariables.CODEC).initializer(PlayerVariables::new));

	public static void variablesLoad() {
		PayloadTypeRegistry.playS2C().register(PlayerVariablesSyncMessage.TYPE, PlayerVariablesSyncMessage.STREAM_CODEC);
		ServerPlayerEvents.JOIN.register((player) -> {
			ServerPlayNetworking.send(player, new PlayerVariablesSyncMessage(player.getAttachedOrCreate(PLAYER_VARIABLES)));
		});
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			ServerPlayNetworking.send(newPlayer, new PlayerVariablesSyncMessage(oldPlayer.getAttachedOrCreate(PLAYER_VARIABLES)));
		});
		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
			if (!destination.isClientSide())
				ServerPlayNetworking.send(player, new PlayerVariablesSyncMessage(player.getAttachedOrCreate(PLAYER_VARIABLES)));
		});
		PlayerEvents.END_PLAYER_TICK.register((entity) -> {
			if (entity instanceof ServerPlayer player && player.getAttachedOrCreate(PLAYER_VARIABLES)._syncDirty) {
				ServerPlayNetworking.send(player, new PlayerVariablesSyncMessage(player.getAttachedOrCreate(PLAYER_VARIABLES)));
				player.getAttachedOrCreate(PLAYER_VARIABLES)._syncDirty = false;
			}
		});
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			PlayerVariables original = oldPlayer.getAttachedOrCreate(PLAYER_VARIABLES);
			PlayerVariables clone = new PlayerVariables();
			clone.TempRandomNumber = original.TempRandomNumber;
			clone.IsOnExercise = original.IsOnExercise;
			if (alive) {
			}
			newPlayer.setAttached(PLAYER_VARIABLES, clone);
		});
	}

	public static class PlayerVariables {
		public static final Codec<PlayerVariables> CODEC = RecordCodecBuilder.create(builder -> builder
				.group(Codec.DOUBLE.fieldOf("TempRandomNumber").orElse(0d).forGetter((vars) -> vars.TempRandomNumber), Codec.BOOL.fieldOf("IsOnExercise").orElse(false).forGetter((vars) -> vars.IsOnExercise)).apply(builder, PlayerVariables::new));
		boolean _syncDirty = false;
		public double TempRandomNumber = 0;
		public boolean IsOnExercise = false;

		public PlayerVariables() {
		}

		public PlayerVariables(double TempRandomNumber, boolean IsOnExercise) {
			this.TempRandomNumber = TempRandomNumber;
			this.IsOnExercise = IsOnExercise;
		}

		public void serialize(ValueOutput output) {
			output.putDouble("TempRandomNumber", TempRandomNumber);
			output.putBoolean("IsOnExercise", IsOnExercise);
		}

		public void deserialize(ValueInput input) {
			TempRandomNumber = input.getDoubleOr("TempRandomNumber", 0);
			IsOnExercise = input.getBooleanOr("IsOnExercise", false);
		}

		public void markSyncDirty() {
			_syncDirty = true;
		}
	}

	public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
		public static final Type<PlayerVariablesSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, "player_variables_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, PlayerVariablesSyncMessage message) -> {
			TagValueOutput output = TagValueOutput.createWithoutContext(ProblemReporter.DISCARDING);
			message.data.serialize(output);
			buffer.writeNbt(output.buildResult());
		}, (RegistryFriendlyByteBuf buffer) -> {
			PlayerVariablesSyncMessage message = new PlayerVariablesSyncMessage(new PlayerVariables());
			message.data.deserialize(TagValueInput.create(ProblemReporter.DISCARDING, buffer.registryAccess(), buffer.readNbt()));
			return message;
		});

		@Override
		public Type<PlayerVariablesSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final PlayerVariablesSyncMessage message, final ClientPlayNetworking.Context context) {
			if (message.data != null) {
				context.client().execute(() -> {
					TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, context.player().registryAccess());
					message.data.serialize(output);
					context.player().getAttachedOrCreate(PLAYER_VARIABLES).deserialize(TagValueInput.create(ProblemReporter.DISCARDING, context.player().registryAccess(), output.buildResult()));
				});
			}
		}
	}
}