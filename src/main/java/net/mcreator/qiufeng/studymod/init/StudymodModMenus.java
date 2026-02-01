/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.qiufeng.studymod.init;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.Minecraft;

import net.mcreator.qiufeng.studymod.world.inventory.*;
import net.mcreator.qiufeng.studymod.network.MenuStateUpdateMessage;
import net.mcreator.qiufeng.studymod.StudymodMod;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

import java.util.Map;

public class StudymodModMenus {
	public static MenuType<CN1Menu> CN_1;
	public static MenuType<MATH1Menu> MATH_1;
	public static MenuType<EN1Menu> EN_1;
	public static MenuType<MATH2Menu> MATH_2;
	public static MenuType<MATH3Menu> MATH_3;
	public static MenuType<MATH4Menu> MATH_4;
	public static MenuType<EN2Menu> EN_2;
	public static MenuType<EN3Menu> EN_3;

	public static void load() {
		CN_1 = register("cn_1", CN1Menu::new);
		CN1Menu.screenInit();
		MATH_1 = register("math_1", MATH1Menu::new);
		MATH1Menu.screenInit();
		EN_1 = register("en_1", EN1Menu::new);
		EN1Menu.screenInit();
		MATH_2 = register("math_2", MATH2Menu::new);
		MATH2Menu.screenInit();
		MATH_3 = register("math_3", MATH3Menu::new);
		MATH3Menu.screenInit();
		MATH_4 = register("math_4", MATH4Menu::new);
		MATH4Menu.screenInit();
		EN_2 = register("en_2", EN2Menu::new);
		EN2Menu.screenInit();
		EN_3 = register("en_3", EN3Menu::new);
		EN3Menu.screenInit();
		PayloadTypeRegistry.playC2S().register(MenuStateUpdateMessage.TYPE, MenuStateUpdateMessage.STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(MenuStateUpdateMessage.TYPE, MenuStateUpdateMessage::handleMenuState);
	}

	public static void clientLoad() {
		PayloadTypeRegistry.playS2C().register(MenuStateUpdateMessage.TYPE, MenuStateUpdateMessage.STREAM_CODEC);
		ClientPlayNetworking.registerGlobalReceiver(MenuStateUpdateMessage.TYPE, MenuStateUpdateMessage::handleClientMenuState);
	}

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				ServerPlayNetworking.send(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof StudymodModScreens.FabricScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPlayNetworking.send(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}

	private static <M extends AbstractContainerMenu> MenuType<M> register(String registryname, MenuType.MenuSupplier<M> element) {
		return Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(StudymodMod.MODID, registryname), new MenuType<>(element, FeatureFlags.DEFAULT_FLAGS));
	}
}