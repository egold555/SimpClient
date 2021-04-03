package clientname.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import clientname.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

/*
 * SimpPicker 9001
 */
public class GuiPickYourSimp extends GuiScreen {

	private static EnumPerson PERSON = EnumPerson.NONE;
	int updateAmount;
	
	public GuiPickYourSimp() {
		this(EnumPerson.NONE);
	}
	
	public GuiPickYourSimp(EnumPerson selected) {
		this.PERSON = selected;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();

		if(PERSON == EnumPerson.NONE) {

			mc.getTextureManager().bindTexture(EnumPerson.POKI.imgbw);
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);

			mc.getTextureManager().bindTexture(EnumPerson.BELLE.imgbw);
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);

			mc.getTextureManager().bindTexture(EnumPerson.NEEKO.imgbw);
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);

			mc.getTextureManager().bindTexture(new ResourceLocation("simpclient/pick_your_simp/title.png"));
			Gui.drawModalRectWithCustomSizedTexture(70, 0, 0, 0, 505, 35, 505, 35);

			//drawRect(2, 49, 237, 313, 0xFF0000FF);

			if(mouseX >= 2 && mouseX <= 237) {
				if(mouseY >= 49 && mouseY <= 313) {
					mc.getTextureManager().bindTexture(EnumPerson.POKI.img);
					Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);
				}
			}

			if(mouseX >= 248 && mouseX <= 429) {
				if(mouseY >= 48 && mouseY <= 313) {
					mc.getTextureManager().bindTexture(EnumPerson.BELLE.img);
					Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);
				}
			}

			if(mouseX >= 439 && mouseX <= 634) {
				if(mouseY >= 47 && mouseY <= 315) {
					mc.getTextureManager().bindTexture(EnumPerson.NEEKO.img);
					Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 640, 360, 640, 360);
				}
			}

			//fontRendererObj.drawString(mouseX + " " + mouseY, 10, 10, -1);

		}
		else {

			this.drawCenteredString(this.fontRendererObj, "You have selected " + EnumChatFormatting.YELLOW + PERSON.name + EnumChatFormatting.RESET + " to simp to." , this.width / 2, this.height / 2 - 50, 16777215);
			this.drawCenteredString(this.fontRendererObj, "Please wait while we customize your playing experience..." , this.width / 2, this.height / 2 - 40, 16777215);
			
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	//Stupid ass hack for the please wait text
	@Override
	public void updateScreen() {
		if(PERSON != EnumPerson.NONE) {
			Client.getInstance().getDiscordRP().update("Simping for:", GuiPickYourSimp.PERSON.name);
			updateAmount++;
			if(updateAmount > 20) {
				
				ResourcePackRepository resourcepackrepository = this.mc.getResourcePackRepository();
	            resourcepackrepository.updateRepositoryEntriesAll();
	            List<ResourcePackRepository.Entry> list = Lists.newArrayList(resourcepackrepository.getRepositoryEntriesAll());
	            list.removeAll(resourcepackrepository.getRepositoryEntries());
	            
	            //list.get(0).
	            
	            List<ResourcePackRepository.Entry> list2 = Lists.<ResourcePackRepository.Entry>newArrayList();
	            
	            for (ResourcePackRepository.Entry r : list)
	            {
	            	if(r.getResourcePackName().equalsIgnoreCase("SIMP" + GuiPickYourSimp.PERSON.shortName)) {
	            		list2.add(r);
	            	}

	            	//list2.add(new ResourcePackListEntryFound(null, resourcepackrepository$entry).func_148318_i());
	            }

	            mc.getResourcePackRepository().setRepositories(list2);
				mc.refreshResources();
				mc.displayGuiScreen(null);
			}
		}
		super.updateScreen();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {


		if(PERSON == EnumPerson.NONE) {

			for(EnumPerson p : EnumPerson.values()) {
				if(p == EnumPerson.NONE) {
					continue;
				}
				if(mouseX >= p.minX && mouseX <= p.maxX) {
					if(mouseY >= p.minY && mouseY <= p.maxY) {
						//poki
						PERSON = p;
					}
				}
			}
//			
//			if(mouseX >= 2 && mouseX <= 237) {
//				if(mouseY >= 49 && mouseY <= 313) {
//					//poki
//					selected = EnumPerson.POKI;
//				}
//			}
//
//			if(mouseX >= 248 && mouseX <= 429) {
//				if(mouseY >= 48 && mouseY <= 313) {
//					//belle
//					selected = EnumPerson.BELLE;
//				}
//			}
//
//			if(mouseX >= 439 && mouseX <= 634) {
//				if(mouseY >= 47 && mouseY <= 315) {
//					//neeko
//					selected = EnumPerson.NEEKO;
//				}
//			}



			final EnumPerson selectedFinal = PERSON;

			if(PERSON != EnumPerson.NONE && mouseButton == 0) {

				GuiYesNo yesNo = new GuiYesNo(new GuiYesNoCallback() {

					@Override
					public void confirmClicked(boolean result, int id) {
						if(!result) {
							mc.displayGuiScreen(new GuiPickYourSimp());
							return;
						}

						mc.displayGuiScreen(new GuiPickYourSimp(PERSON));

					}
				}, "You have chosen " + EnumChatFormatting.YELLOW + PERSON.name + EnumChatFormatting.RESET + " to simp for.", "Are you sure you want to do this?", 69);

				mc.displayGuiScreen(yesNo);

			}
		}
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public static enum EnumPerson {
		
		NONE("Error", "error", 0, 0, 0, 0, EnumChatFormatting.WHITE, EnumChatFormatting.WHITE, Color.WHITE, Color.WHITE),
		
		POKI(
				"Pokimane", 
				"poki", 
				2, 237, 49, 313, 
				EnumChatFormatting.AQUA, EnumChatFormatting.WHITE,
				Color.CYAN, Color.WHITE
				), 
		
		BELLE(
				"Belle Delphine", 
				"belle", 
				248, 429, 48, 313, 
				EnumChatFormatting.LIGHT_PURPLE, EnumChatFormatting.WHITE,
				Color.MAGENTA, Color.WHITE
				), 
		
		NEEKO(
				"Neekolul", 
				"neeko", 439, 634, 47, 315,
				EnumChatFormatting.YELLOW, EnumChatFormatting.WHITE,
				Color.YELLOW, Color.WHITE
				);
		
		public final String name, shortName;
		private final ResourceLocation img, imgbw;
		private int minX, maxX, minY, maxY;
		public final EnumChatFormatting primaryColor, secondaryColor;
		public final Color primaryColorJ, secondaryColorJ;
		private EnumPerson(String name, String shortName, int minX, int maxX, int minY, int maxY, EnumChatFormatting primaryColor, EnumChatFormatting secondaryColor, Color primaryColorJ, Color secondaryColorJ) {
			this.name = name;
			this.shortName = shortName;
			this.img = new ResourceLocation("simpclient/pick_your_simp/" + shortName + ".png");
			this.imgbw = new ResourceLocation("simpclient/pick_your_simp/" + shortName + "_bw.png");
			this.minX = minX;
			this.minY = minY;
			this.maxX = maxX;
			this.maxY = maxY;
			this.primaryColor = primaryColor;
			this.secondaryColor = secondaryColor;
			this.primaryColorJ = primaryColorJ;
			this.secondaryColorJ = secondaryColorJ;
		}
	}
	
	public static EnumPerson getPerson() {
		if(PERSON == EnumPerson.NONE) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiPickYourSimp());
		}
		return PERSON;
	}

}
