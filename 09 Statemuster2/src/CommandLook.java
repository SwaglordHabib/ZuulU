import java.util.Scanner;

public class CommandLook implements ICommands {

	private Entity e;

	private Scanner scanner;
	private static ICommands instance = new CommandLook();

	public static ICommands getInstance() {
		if (instance == null) {
			instance = new CommandLook();
		}
		return instance;
	}

	private CommandLook() {

	}

	public void init(Entity e) {
		this.e = e;
	}

	@Override
	public void execute() {
		System.out.println(this.e.getAktuellerRaum().getLangeBeschreibung());
	}

	@Override
	public String getDescription() {
		return "Mit dem Befehl 'look', schaut man sich um und entdeckt alle Items in dem Raum worin man sich befindet.";
	}

	@Override
	public String getCommand() {
		return "look";
	}

	@Override
	public String getSyntax() {
		return "Befehl 'look': look";
	}

}
