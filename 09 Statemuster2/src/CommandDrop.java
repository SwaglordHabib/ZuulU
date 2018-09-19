
public class CommandDrop implements ICommands {
	private Scanner scanner;
	private static ICommands instance = new CommandDrop();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandGo();
		}
		return instance;
	}

	private CommandDrop() {

	}

	@Override
	public void execute() {
		System.out.println("Welchen Gegenstand möchten Sie aufnehmen?");

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return null;
	}

}
