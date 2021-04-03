package clientname.mods.impl;

import org.lwjgl.opengl.GL11;

import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModArmorStatus extends ModDraggable {

	@Override
	public int getWidth() {
		return 64;
	}

	@Override
	public int getHeight() {
		return 64;
	}

	@Override
	public void render(ScreenPosition pos) {
		for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			renderItemStack(pos, i, itemStack);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
		
		if(is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		
		int yAdd = (-16 * i) + 48;
		
		if(is.getItem().isDamageable()) {
			double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
			font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
		
		GL11.glPopMatrix();
		
	}

}
