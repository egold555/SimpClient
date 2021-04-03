package clientname.command;

import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;

/*
 * THis is a test command, what do you expect it to do? Give you OP?
 */
public class CommandTest extends Command {

	public CommandTest() {
		super("test");
	}

	@Override
	public void execute(EntityPlayerSP sender, List<String> args) {
		System.out.println("It worked!");
		for(String s : args) {
			System.out.print(s + ",");
		}
	}

}
