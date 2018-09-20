
public class CommandHelp implements ICommands {

	private static ICommands instance = new CommandHelp();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandHelp();
		}
		return instance;
	}

	private CommandHelp() {

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
