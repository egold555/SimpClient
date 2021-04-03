package clientname.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class GuiPasswordField extends GuiTextField {

    public GuiPasswordField(FontRenderer renderer, int posx, int posy, int x, int y) {
        super(1, renderer, posx, posy, x, y);
    }

    public void drawTextBox() {
        String s = this.getPW();
        setText(this.getText());
        super.drawTextBox();
        this.setText(s);
    }

    @Override
    public String getText() {
        return super.getText().replaceAll(".", "\u25CF");
    }

    public String getPW() {
        return super.getText();
    }

}