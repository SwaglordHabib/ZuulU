import java.util.HashMap;

public class Invoker {

	private CommandGo commandGo;
	private CommandTake commandTake;
	private CommandDrop commandDrop;
	private CommandEat commandEat;
	private CommandQuit commandQuit;
	private CommandLook commandLook;
	private CommandHelp commandHelp;

	HashMap<String, ICommands> commandList = new HashMap<String, ICommands>();

	/**
	 * Initialisiert den Invoker, sowie die dazugehörigen Befehle
	 */
	public Invoker() {
		commandList.put("go", commandGo);
		commandList.put("eat", commandEat);
		commandList.put("drop", commandDrop);
		commandList.put("take", commandTake);
		commandList.put("look", commandLook);
		commandList.put("help", commandHelp);
		commandList.put("quit", commandQuit);
	}

	/**
	 * Prüft, ob der angegebene Befehl ein gültiger Befehl ist
	 * @param Command
	 * @return 'true', wenn der Befehl gültig ist
	 */
	public boolean isCommand(String Command) {
		if (commandList.containsKey(Command)) {
			return true;
		}
		return false;
	}
}
