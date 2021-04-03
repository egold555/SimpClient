package net.montoyo.mcef.example;

import clientname.event.EventManager;
import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import clientname.event.impl.RenderGameOverlayEvent;
import net.minecraft.client.Minecraft;
import net.montoyo.mcef.api.API;
import net.montoyo.mcef.api.IBrowser;
import net.montoyo.mcef.api.IDisplayHandler;
import net.montoyo.mcef.api.IJSQueryCallback;
import net.montoyo.mcef.api.IJSQueryHandler;
import net.montoyo.mcef.api.MCEFApi;
import net.montoyo.mcef.utilities.Log;

/**
 * An example mod that shows you how to use MCEF.
 * Assuming that it is client-side only and that onInit() is called on initialization.
 * This example shows a simple 2D web browser when pressing F6.
 * 
 * @author montoyo
 *
 */
public class ExampleMod implements IDisplayHandler, IJSQueryHandler {
    
    public static ExampleMod INSTANCE;

    public ScreenCfg hudBrowser = null;
    private Minecraft mc = Minecraft.getMinecraft();
    private BrowserScreen backup = null;
    private API api;

    public API getAPI() {
        return api;
    }

    public void onPreInit() {
    	System.out.println("Fuck");
        //Grab the API and make sure it isn't null.
        api = MCEFApi.getAPI();
        if(api == null)
            return;

        api.registerScheme("mod", ModScheme.class, true, false, false, true, true, false, false);
    }
    
    public void onInit() {
        INSTANCE = this;
        
        System.out.println("-- Registering screen");
        EventManager.register(this);

        if(api != null) {
            //Register this class to handle onAddressChange and onQuery events
            api.registerDisplayHandler(this);
            api.registerJSQueryHandler(this);
        }
    }
    
    public void setBackup(BrowserScreen bu) {
        backup = bu;
    }
    
    public boolean hasBackup() {
        return (backup != null);
    }
    
    public void showScreen(String url) {
        if(mc.currentScreen instanceof BrowserScreen)
            ((BrowserScreen) mc.currentScreen).loadURL(url);
        else if(hasBackup()) {
            mc.displayGuiScreen(backup);
            backup.loadURL(url);
            backup = null;
        } else
            mc.displayGuiScreen(new BrowserScreen(url));
    }
    
    public IBrowser getBrowser() {
        if(mc.currentScreen instanceof BrowserScreen)
            return ((BrowserScreen) mc.currentScreen).browser;
        else if(backup != null)
            return backup.browser;
        else
            return null;
    }
    
    @EventTarget
    public void onTick(ClientTickEvent ev) {
    	//System.out.println("t");
    	if(Minecraft.getMinecraft().gameSettings.CLIENT_SIMP_BROWSER.isPressed() && !(mc.currentScreen instanceof BrowserScreen)) {
            //Display the web browser UI.
            mc.displayGuiScreen(hasBackup() ? backup : new BrowserScreen());
            backup = null;
        }
    }

    @Override
    public void onAddressChange(IBrowser browser, String url) {
        //Called by MCEF if a browser's URL changes. Forward this event to the screen.
        if(mc.currentScreen instanceof BrowserScreen)
            ((BrowserScreen) mc.currentScreen).onUrlChanged(browser, url);
        else if(hasBackup())
            backup.onUrlChanged(browser, url);
    }

    @Override
    public void onTitleChange(IBrowser browser, String title) {
    }

    @Override
    public void onTooltip(IBrowser browser, String text) {
    }

    @Override
    public void onStatusMessage(IBrowser browser, String value) {
    }

    @Override
    public boolean handleQuery(IBrowser b, long queryId, String query, boolean persistent, IJSQueryCallback cb) {
        if(b != null && query.equalsIgnoreCase("username")) {
            if(b.getURL().startsWith("mod://")) {
                //Only allow MCEF URLs to get the player's username to keep his identity secret

                mc.addScheduledTask(() -> {
                    //Add this to a scheduled task because this is NOT called from the main Minecraft thread...

                    try {
                        String name = mc.getSession().getUsername();
                        cb.success(name);
                    } catch(Throwable t) {
                        cb.failure(500, "Internal error.");
                        Log.warning("Could not get username from JavaScript:");
                        t.printStackTrace();
                    }
                });
            } else
                cb.failure(403, "Can't access username from external page");
            
            return true;
        }
        
        return false;
    }

    @Override
    public void cancelQuery(IBrowser b, long queryId) {
    }

    @EventTarget
    public void onDrawHUD(RenderGameOverlayEvent ev) {
        if(hudBrowser != null)
            hudBrowser.drawScreen(0, 0, 0.f);
    }

}
