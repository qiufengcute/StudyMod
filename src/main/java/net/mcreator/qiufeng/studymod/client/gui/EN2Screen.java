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

import net.mcreator.qiufeng.studymod.world.inventory.EN2Menu;
import net.mcreator.qiufeng.studymod.procedures.GetCanCloseTextProcedure;
import net.mcreator.qiufeng.studymod.network.EN2ButtonMessage;
import net.mcreator.qiufeng.studymod.init.StudymodModScreens;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class EN2Screen extends AbstractContainerScreen<EN2Menu> implements StudymodModScreens.FabricScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_axxx;
	private Button button_bxxx;
	private Button button_cxxx;
	private Button button_dxxx;

	public EN2Screen(EN2Menu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 346;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	private static final ResourceLocation texture = ResourceLocation.parse("studymod:textures/screens/en_2.png");

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
		guiGraphics.drawString(this.font, Component.translatable("gui.studymod.en_2.label_xxxxxxxxxxxxxxx"), 18, 19, -12829636, false);
		guiGraphics.drawString(this.font, GetCanCloseTextProcedure.execute(entity), 7, 5, -16711732, false);
	}

	@Override
	public void init() {
		super.init();
		button_axxx = Button.builder(Component.translatable("gui.studymod.en_2.button_axxx"), e -> {
			int x = EN2Screen.this.x;
			int y = EN2Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN2ButtonMessage(0, x, y, z));
				EN2ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 89, this.topPos + 38, 164, 20).build();
		this.addRenderableWidget(button_axxx);
		button_bxxx = Button.builder(Component.translatable("gui.studymod.en_2.button_bxxx"), e -> {
			int x = EN2Screen.this.x;
			int y = EN2Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN2ButtonMessage(1, x, y, z));
				EN2ButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 90, this.topPos + 70, 165, 20).build();
		this.addRenderableWidget(button_bxxx);
		button_cxxx = Button.builder(Component.translatable("gui.studymod.en_2.button_cxxx"), e -> {
			int x = EN2Screen.this.x;
			int y = EN2Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN2ButtonMessage(2, x, y, z));
				EN2ButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 79, this.topPos + 99, 188, 20).build();
		this.addRenderableWidget(button_cxxx);
		button_dxxx = Button.builder(Component.translatable("gui.studymod.en_2.button_dxxx"), e -> {
			int x = EN2Screen.this.x;
			int y = EN2Screen.this.y;
			if (true) {
				ClientPlayNetworking.send(new EN2ButtonMessage(3, x, y, z));
				EN2ButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 94, this.topPos + 131, 160, 20).build();
		this.addRenderableWidget(button_dxxx);
	}
}