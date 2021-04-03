package clientname.mods;

import clientname.gui.hud.HUDManager;
import clientname.mods.impl.ModArmorStatus;
import clientname.mods.impl.ModCPS;
import clientname.mods.impl.ModGlintColor;
import clientname.mods.impl.ModKeystrokes;
import clientname.mods.impl.ModPerspective;
import clientname.mods.impl.ModTier3Sub;

public class ModInstances {

	//private static ModHelloWorld modHelloWorld;
	
	private static ModArmorStatus modArmorStatus;
	
	private static ModKeystrokes modKeystrokes;

	private static ModCPS modCPS;
	
	private static ModPerspective modPerspective;
	
	private static ModGlintColor glintColor;
	
	public static void register(HUDManager api) {
		
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
		
		modKeystrokes = new ModKeystrokes();
		api.register(modKeystrokes);
		
		
		modCPS = new ModCPS();
		api.register(modCPS);
		
		modPerspective = new ModPerspective();
		api.register(modPerspective);

		glintColor = new ModGlintColor();
		
		api.register(new ModTier3Sub());
		
	}
	
	public static ModPerspective getModPerspective() {
		return modPerspective;
	}
	
	public static ModGlintColor getModGlintColor() {
		return glintColor;
	}

	
}
