package clientname.command;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Command {

	private final String name;
	protected final Minecraft mc;
	
	public Command(String name){
        this.name = name;
        mc = Minecraft.getMinecraft();
    }
	
	public abstract void execute(EntityPlayerSP sender, List<String> args);
	
	public String getName() {
		return name;
	}
	
}
