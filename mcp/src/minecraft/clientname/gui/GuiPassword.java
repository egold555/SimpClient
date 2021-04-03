package clientname.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiPassword extends GuiScreen {

	 private GuiTextField username;
	    private GuiPasswordField pw;
	    private GuiButton login;
	    private GuiButton cancel;

	    private GuiScreen prev;

	    private int basey;

	    private String message = "";

	   public GuiPassword(GuiScreen prev) {
	        this.mc = Minecraft.getMinecraft();
	        this.fontRendererObj = mc.fontRendererObj;
	        this.prev = prev;
	    }

	    @Override
	    protected void actionPerformed(GuiButton b) {
	        switch (b.id) {
	            case 0:
	                if (login())
	                    this.mc.displayGuiScreen(prev);
	                break;
	            case 1:
	                this.mc.displayGuiScreen(prev);
	                break;
	        }

	    }

	    @Override
	    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
	        this.drawDefaultBackground();

	        this.drawCenteredString(this.fontRendererObj, "Username/E-Mail:", this.width / 2, this.basey,
	                Color.WHITE.getRGB());
	        this.drawCenteredString(this.fontRendererObj, "Password:", this.width / 2, this.basey + 45,
	                Color.WHITE.getRGB());
	        if (!(this.message == null || this.message.isEmpty())) {
	            this.drawCenteredString(this.fontRendererObj, this.message, this.width / 2, this.basey - 15, 0xFFFFFF);
	        }
	        this.username.drawTextBox();
	        this.pw.drawTextBox();

	        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	    }

	    @Override
	    public void updateScreen() {
	        super.updateScreen();
	        this.username.drawTextBox();
	        this.pw.drawTextBox();
	    }

	    @Override
	    public void initGui() {
	        super.initGui();

	        this.basey = this.height / 2 - 110 / 2;

	        this.username = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 155, this.basey + 15, 2 * 155, 20);
	        this.username.setMaxStringLength(512);
	        this.username.setFocused(true);

	        this.pw = new GuiPasswordField(this.fontRendererObj, this.width / 2 - 155, this.basey + 60, 2 * 155, 20);
	        this.pw.setMaxStringLength(512);

	        this.login = new GuiButton(0, this.width / 2 - 155, this.basey + 105, 153, 20, "Login");
            this.cancel = new GuiButton(1, this.width / 2 + 2, this.basey + 105, 155, 20, "Cancel");
            this.buttonList.add(this.login);
            this.buttonList.add(this.cancel);

	    }

	    @Override
	    protected void keyTyped(char c, int k) throws IOException {
	        super.keyTyped(c, k);
	        this.username.textboxKeyTyped(c, k);
	        this.pw.textboxKeyTyped(c, k);
	        if (k == Keyboard.KEY_TAB) {
	            this.username.setFocused(!this.username.isFocused());
	            this.pw.setFocused(!this.pw.isFocused());
	        } else if (k == Keyboard.KEY_RETURN) {
	            if (this.username.isFocused()) {
	                this.username.setFocused(false);
	                this.pw.setFocused(true);
	            } else if (this.pw.isFocused()) {
	                this.actionPerformed(this.login);
	            }
	        }
	    }

	    @Override
	    protected void mouseClicked(int x, int y, int b) throws IOException {
	        super.mouseClicked(x, y, b);
	        this.username.mouseClicked(x, y, b);
	        this.pw.mouseClicked(x, y, b);
	    }

	    /**
	     * used as an interface between this and the secure class
	     * <p>
	     * returns whether the login was successful
	     */
	    private boolean login() {
	    	
	    	if(Math.random() <= 0.7) {
	    		this.message = (char) 167 + "4Login failed: Wrong username / password";
	    		this.pw.setText("");
	    		return false;
	    	}
	    	
	    	this.message = (char) 167 + "aLogin successful!";
	    	
			return true;
	    	
	    }
	
}
