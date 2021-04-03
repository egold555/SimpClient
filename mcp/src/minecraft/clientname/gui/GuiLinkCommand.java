package clientname.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GuiLinkCommand extends GuiScreen {

	private static boolean twitch, twitter, instagram, onlyfans;
	
	public GuiLinkCommand() {
		this(-1);
	}
	
	public GuiLinkCommand(int id) {
		switch(id) {
		case 0: twitch = true; break;
		case 1: twitter = true; break;
		case 2: instagram = true; break;
		case 3: onlyfans = true; break;
		}
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, 20, 20, "Login to Twitch " + (twitch ? EnumChatFormatting.GREEN + "✓" : EnumChatFormatting.RED + "✗")));
		this.buttonList.add(new GuiButton(1, 20, 40, "Login to Twitter " + (twitter ? EnumChatFormatting.GREEN + "✓" : EnumChatFormatting.RED + "✗")));
		this.buttonList.add(new GuiButton(2, 20, 60, "Login to Instagram " + (instagram ? EnumChatFormatting.GREEN + "✓" : EnumChatFormatting.RED + "✗")));
		this.buttonList.add(new GuiButton(3, 20, 80, "Login to OnlyFans " + (onlyfans ? EnumChatFormatting.GREEN + "✓" : EnumChatFormatting.RED + "✗")));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch(button.id) {
		case 0: mc.displayGuiScreen(new GuiPassword(new GuiLinkCommand(0))); break;
		case 1: mc.displayGuiScreen(new GuiPassword(new GuiLinkCommand(1))); break;
		case 2: mc.displayGuiScreen(new GuiPassword(new GuiLinkCommand(2))); break;
		case 3: mc.displayGuiScreen(new GuiPassword(new GuiLinkCommand(3))); break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public static boolean isTwitchChecked() {
		return twitch;
	}
	
	public static boolean isOnlyFansChecked() {
		return instagram;
	}
	
	public static boolean isInstagramChecked() {
		return twitch;
	}
	
	public static boolean isTwitterChecked() {
		return twitter;
	}
	
}
