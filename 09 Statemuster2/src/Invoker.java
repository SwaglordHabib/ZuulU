import java.util.ArrayList;
import java.util.List;

public class Invoker {

	private CommandGo commandGo = (CommandGo) CommandGo.getInstance();
	private CommandTake commandTake;
	private CommandDrop commandDrop;
	private CommandEat commandEat;
	private CommandQuit commandQuit;
	private CommandLook commandLook;
	private CommandHelp commandHelp;
	List<ICommands> CommandList = new ArrayList<ICommands>();

	public Invoker() {
		CommandList.add(commandGo);
		CommandList.add(commandEat);
		CommandList.add(commandDrop);
		CommandList.add(commandTake);
		CommandList.add(commandLook);
		CommandList.add(commandHelp);
		CommandList.add(commandQuit);
	}

	public boolean isCommand(String Command) {
		for (ICommands c : CommandList) {
			if (c.getCommand().toLowerCase() == Command)
				return true;
		}

		return false;
	}

	public void Test(Befehl befehl) {

	}

	public void setCommandGo(ICommands commandGo) {
		this.commandGo = (CommandGo) commandGo;
	}

	public void setCommandGo(CommandGo commandGo) {
		this.commandGo = commandGo;
	}

	public void setCommandTake(CommandTake commandTake) {
		this.commandTake = commandTake;
	}

	public void setCommandDrop(CommandDrop commandDrop) {
		this.commandDrop = commandDrop;
	}

	public void setCommandEat(CommandEat commandEat) {
		this.commandEat = commandEat;
	}

	public void setCommandQuit(CommandQuit commandQuit) {
		this.commandQuit = commandQuit;
	}

	public void setCommandLook(CommandLook commandLook) {
		this.commandLook = commandLook;
	}

	public void setCommandHelp(CommandHelp commandHelp) {
		this.commandHelp = commandHelp;
	}

	public void doCommandGo(Entity e, Raum raum, ArrayList<Raum> raumliste) {
		commandGo.init(e, raum, raumliste);
		commandGo.execute();
	}

	public void doCommandTake(Spieler e) {
		commandTake.init(e);
		commandTake.execute();
	}

	public void doCommandLook(Entity e) {
		commandLook.init(e);
		commandLook.execute();
	}

	public void doCommandDrop(Spieler e, Raum raum) {
		commandDrop.init(e, raum);
		commandDrop.execute();
	}

	public void doCommandEat(Spieler e) {
		commandEat.init(e);
		commandEat.execute();
	}

	public void doCommaQuit() {
		commandQuit.init();
		commandQuit.execute();
	}

	public void doCommandHelp() {
		commandHelp.init();
		commandHelp.execute();
	}
}
