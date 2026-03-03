package net.mcreator.qiufeng.studymod;

import net.mcreator.qiufeng.studymod.network.StudymodModVariables;
import net.mcreator.qiufeng.studymod.init.StudymodModScreens;
import net.mcreator.qiufeng.studymod.init.StudymodModMenus;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ClientModInitializer;

@Environment(EnvType.CLIENT)
public class StudymodModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		StudymodModScreens.clientLoad();
		StudymodModMenus.clientLoad();
		ClientPlayNetworking.registerGlobalReceiver(StudymodModVariables.PlayerVariablesSyncMessage.TYPE, StudymodModVariables.PlayerVariablesSyncMessage::handleData);
		// Start of user code block mod init
		// End of user code block mod init
	}
	// Start of user code block mod methods
	// End of user code block mod methods
}