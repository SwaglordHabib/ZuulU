
public class CommandQuit implements ICommands {

	private static ICommands instance = new CommandQuit();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandQuit();
		}
		return instance;
	}

	private CommandQuit() {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

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

	public void init() {
		// TODO Auto-generated method stub

	}

}
