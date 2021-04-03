package clientname.command;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import clientname.event.EventManager;
import clientname.event.EventTarget;
import clientname.event.impl.ClientTickEvent;
import clientname.gui.GuiLinkCommand;
import net.minecraft.client.entity.EntityPlayerSP;

/*
 * The actual only important command
 */
public class CommandLink extends Command {

	public CommandLink() {
		super("link");
		EventManager.register(this);
	}

	
	//shitty hack
	@Override
	public void execute(EntityPlayerSP sender, List<String> args) {
		ticks = 0;
	}

	int ticks = -1;
	@EventTarget
	public void onTick(ClientTickEvent e) {
		if(ticks != -1) {
			ticks++;
			System.out.println("ticks");
		}
		
		if(ticks == 2) {
			//EventManager.unregister(this);
			mc.displayGuiScreen(new GuiLinkCommand());
			ticks = -1;
		}
		
		
	}
	
}
