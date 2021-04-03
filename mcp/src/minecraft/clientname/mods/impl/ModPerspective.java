package clientname.mods.impl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import clientname.event.EventTarget;
import clientname.event.impl.KeyEvent;
import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;

public class ModPerspective extends ModDraggable {

	private boolean returnOnRelease = true; //hold down the key = true
	private boolean perspectiveToggled = false;
	
	private float cameraYaw = 0F;
	private float cameraPitch = 0F;
	
	private int previousPerspective = 0; //pref f5 state
	
	@EventTarget
	public void keyboardEvent(KeyEvent e) {
		
		if(Keyboard.getEventKey() == mc.gameSettings.CLIENT_PERSPECTIVE.getKeyCode()) {
			
			if(Keyboard.getEventKeyState()) {
				perspectiveToggled = !perspectiveToggled;
				
				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;
				
				if(perspectiveToggled) {
					previousPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				}
				else {
					mc.gameSettings.thirdPersonView = previousPerspective;
				}
				
			}
			else if(returnOnRelease) {
				perspectiveToggled = false;
				mc.gameSettings.thirdPersonView = previousPerspective;
			}
			
		}
		
		if(Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			perspectiveToggled = false;
		}
		
	}
	
	public float getCameraYaw() {
		return perspectiveToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return perspectiveToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	public boolean overrideMouse() {
		
		if(mc.inGameHasFocus && Display.isActive()) {
			
			if(!perspectiveToggled) {
				return true;
			}
			
			mc.mouseHelper.mouseXYChange();
			float f1 = mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
			float f2 = f1 * f1 * f1 * 8.0F;
			float f3 = (float) mc.mouseHelper.deltaX * f2;
			float f4 = (float) mc.mouseHelper.deltaY * f2;
			
			cameraYaw += f3 * 0.15F;
			cameraPitch += f4 * 0.15F;
			
			if (cameraPitch > 90) cameraPitch = 90;
			if (cameraPitch < -90) cameraPitch = -90;
			
			
		}
		
		return false;
		
	}
	
	
	@Override
	public int getWidth() {
		return font.getStringWidth("[Perspective Toggled]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(perspectiveToggled) {
			font.drawString("[Perspective Toggled]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[Perspective Toggled]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	}

}
