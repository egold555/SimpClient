package clientname.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.util.EnumChatFormatting;

public class CommandManager {

	private static final String PREFIX = "/";
	
	private static CommandManager instance;
	
	private List<Command> commands = new ArrayList<Command>();
	
	public static CommandManager getInstance() {
		if(instance == null) {
			instance = new CommandManager();
		}
		return instance;
	}
	
	public CommandManager() {
		register(new CommandTest());
		commands.add(new CommandLink());
		commands.add(new CommandTier3Simp());
	}
	
	public void register(Command cmd) {
		commands.add(cmd);
	}
	
	public boolean tryExecute(String in) {
		
		for(Command c : commands) {
			if(in.split(" ")[0].equalsIgnoreCase(PREFIX + c.getName())) {
				c.execute(Minecraft.getMinecraft().thePlayer, getArgs(in));
				return true;
			}
		}
		
		return false;
	}
	
	private List<String> getArgs(String text) {
        
        final List<String> args = new ArrayList<>();

        final String[] split = text.substring(1).split(" ");

        int beginIndex = 1;

        for (int i = beginIndex; i < split.length; i++){
            final String arg = split[i];

            if (arg == null)
                continue;

            args.add(arg);
        }

        return args;
    }

	public void onTab(String s, List<String> list) {
		
		for(Command cmd : commands) {
			if (CommandBase.doesStringStartWith(s, cmd.getName()))
	        {
	            list.add(cmd.getName());
	        }
		}
		
		
	}

	
}
