package clientname.mods.impl;

import clientname.gui.GuiLinkCommand;
import clientname.gui.GuiPickYourSimp;
import clientname.gui.GuiPickYourSimp.EnumPerson;
import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;

public class ModTier3Sub extends ModDraggable {

	@Override
	public int getWidth() {
		return 120;
	}

	@Override
	public int getHeight() {
		return 100;
	}

	@Override
	public void render(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), 0xAA555555);

		if(GuiPickYourSimp.getPerson() == null) {
			font.drawString("Person is null ?!?", pos.getAbsoluteX(), pos.getAbsoluteY(), 0xFF0000);
			return;
		}
		
		String billing = "";
		String date = "";
		
		if(GuiPickYourSimp.getPerson() == EnumPerson.BELLE) {
			
			billing = "OnlyFans Billing Cycle:";
			date = "April 5th 2021";
			
			if(!GuiLinkCommand.isOnlyFansChecked()) {
				date = "Not linked! Use /link";
			}
		}
		else if(GuiPickYourSimp.getPerson() == EnumPerson.POKI) {
			billing = "Tier 3 Billing Cycle:";
			date = "April 13th 2021";
			
			if(!GuiLinkCommand.isTwitchChecked()) {
				date = "Not linked! Use /link";
			}
		}
		else if(GuiPickYourSimp.getPerson() == EnumPerson.NEEKO) {
			billing = "Tier 3 Billing Cycle:";
			date = "April 23rd 2021";
			
			if(!GuiLinkCommand.isTwitchChecked()) {
				date = "Not linked! Use /link";
			}
		}
		
		font.drawString(GuiPickYourSimp.getPerson().secondaryColor + "Simping For:", pos.getAbsoluteX() + 4, pos.getAbsoluteY(), -1);
		font.drawString(GuiPickYourSimp.getPerson().primaryColor + GuiPickYourSimp.getPerson().name, pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT, -1);
		font.drawString(GuiPickYourSimp.getPerson().secondaryColor + billing, pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT * 3, -1);
		font.drawString(GuiPickYourSimp.getPerson().primaryColor + date, pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT * 4, -1);
		
		font.drawString(GuiPickYourSimp.getPerson().secondaryColor + "Following on:", pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT * 6, -1);
		font.drawString("  - " + GuiPickYourSimp.getPerson().primaryColor + "Instagram: " + (GuiLinkCommand.isInstagramChecked() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + GuiLinkCommand.isInstagramChecked(), pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT * 7, -1);
		font.drawString("  - " + GuiPickYourSimp.getPerson().primaryColor +"Twitter: " + (GuiLinkCommand.isTwitterChecked() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + GuiLinkCommand.isTwitterChecked(), pos.getAbsoluteX() + 4, pos.getAbsoluteY() + font.FONT_HEIGHT * 8, -1);
	}

}
