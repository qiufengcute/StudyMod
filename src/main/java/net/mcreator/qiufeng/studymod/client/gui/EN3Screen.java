package net.mcreator.qiufeng.studymod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.qiufeng.studymod.world.inventory.EN3Menu;
import net.mcreator.qiufeng.studymod.procedures.GetCanCloseTextProcedure;
import net.mcreator.qiufeng.studymod.network.EN3ButtonMessage;
import net.mcreator.qiufeng.studymod.init.StudymodModScreens;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class EN3Screen extends AbstractContainerScreen<EN3Menu> implements StudymodModScreens.FabricScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_axxx;
	private Button button_bxxx;
	private Button button_cxxx;
	private Button button_dxxx;

	public EN3Screen(EN3Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 340;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("studymod:textures/screens/en_3.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.studymod.en_3.label_xxxxxxxxxxxxxxx"), 20, 20, -12829636, false);
		guiGraphics.drawString(this.font, GetCanCloseTextProcedure.execute(entity), 5, 8, -16711732, false);
	}

	@Override
	public void init() {
		super.init();
		button_axxx = Button.builder(Component.translatable("gui.studymod.en_3.button_axxx"), e -> {
			int x = EN3Screen.this.x;
			int y = EN3Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN3ButtonMessage(0, x, y, z));
				EN3ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 33, this.topPos + 45, 281, 20).build();
		this.addRenderableWidget(button_axxx);
		button_bxxx = Button.builder(Component.translatable("gui.studymod.en_3.button_bxxx"), e -> {
			int x = EN3Screen.this.x;
			int y = EN3Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN3ButtonMessage(1, x, y, z));
				EN3ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 33, this.topPos + 71, 281, 20).build();
		this.addRenderableWidget(button_bxxx);
		button_cxxx = Button.builder(Component.translatable("gui.studymod.en_3.button_cxxx"), e -> {
			int x = EN3Screen.this.x;
			int y = EN3Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN3ButtonMessage(2, x, y, z));
				EN3ButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 35, this.topPos + 97, 278, 20).build();
		this.addRenderableWidget(button_cxxx);
		button_dxxx = Button.builder(Component.translatable("gui.studymod.en_3.button_dxxx"), e -> {
			int x = EN3Screen.this.x;
			int y = EN3Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN3ButtonMessage(3, x, y, z));
				EN3ButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 125, 311, 20).build();
		this.addRenderableWidget(button_dxxx);
	}
}