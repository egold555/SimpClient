package clientname;

import clientname.event.EventManager;
import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import clientname.event.impl.EventNametagRender;
import clientname.event.impl.EventTabRenderPlayer;
import clientname.gui.SplashProgress;
import clientname.gui.hud.HUDManager;
import clientname.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.montoyo.mcef.MCEF;

public class Client {

	private static final Client INSTANCE = new Client();
	public static final Client getInstance() { 
		return INSTANCE; 
	}

	private DiscordRP discordRP = new DiscordRP();

	private HUDManager hudManager;


	public void init() {
		System.out.println("init");
		FileManager.init();
		SplashProgress.setProgress(1, "Client - Initalising Discord RP!");
		discordRP.start();
		EventManager.register(this);

		MCEF.init();
		
	}

	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
		
	}

	public void shutdown() {
		discordRP.shutdown();
		MCEF.onMinecraftShutdown();
	}

	public DiscordRP getDiscordRP() {
		return discordRP;
	}

	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}

	}
	
	private static final ResourceLocation ICON_SIMP_CLIENT = new ResourceLocation("simpclient/logo.png");
	
	@EventTarget
	public void onNametagRnder(EventNametagRender e) {
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(ICON_SIMP_CLIENT);
        Gui.drawModalRectWithCustomSizedTexture(-Minecraft.getMinecraft().fontRendererObj.getStringWidth(e.p.getDisplayName().getFormattedText()) / 2 - 12, -2, 10, 10, 10, 10, 10, 10);

		
	}
	
	
	@EventTarget
	public void onTabRender(EventTabRenderPlayer e) {
		if(e.p != null) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(ICON_SIMP_CLIENT);
            Gui.drawScaledCustomSizeModalRect(e.p_175245_2_ + e.p_175245_1_ - 0, e.p_175245_3_, 64, 64, 64, 64, 8, 8, 64, 64);
			
		}
	}

}
