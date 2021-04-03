package clientname.mods.impl;

import java.awt.Color;

import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import clientname.gui.GuiPickYourSimp;
import clientname.mods.Mod;

public class ModGlintColor extends Mod {

	private static final Color DEFAULT_COLOR = new Color(128, 64, 204);
	
	public Color getColor() {
		
		if(isEnabled()) {
			return GuiPickYourSimp.getPerson().primaryColorJ;
		}
		else {
			return DEFAULT_COLOR;
		}
		
	}
	
}
